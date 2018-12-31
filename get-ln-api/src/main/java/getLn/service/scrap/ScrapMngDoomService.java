package getLn.service.scrap;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import getLn.model.ChapterDto;
import getLn.service.FileCreationService;
import getLn.service.exception.ChapterNotOutException;
import getln.data.entity.Chapter;
import getln.data.entity.Manga;
import getln.service.common.ChapterSqlService;
import getln.service.common.MangaSqlService;
import lombok.extern.slf4j.Slf4j;

/**
 * .
 */
@Slf4j
@Service
public class ScrapMngDoomService extends ScrapService {


    @Inject
    private MangaSqlService mangaService;

    @Inject
    private ChapterSqlService chapterService;

    @Inject
    private FileCreationService fileCreationService;

    private final String MANGADOOM_URL = "http://www.mngdoom.com/";

    /**
     * Get all manga from the news page
     */
    public List<ChapterDto> scanLastScanOutPage()
            throws IOException {
        try {
            List<ChapterDto> newChapters = new ArrayList<>();
            Connection connect = Jsoup.connect(MANGADOOM_URL);
            final Document doc = addInfo(connect).get();
            Element manga_updates = doc.getElementsByClass("manga_updates").get(0);
            Elements allManga = manga_updates.getElementsByTag("dl");
            allManga.forEach(element -> {
                //we get one line of last manga update
                Element elementTitle = element.getElementsByClass("manga-info-qtip").get(0);
                String urlElement = elementTitle.toString().split("href=\"")[1].split("\" ")[0];

                //we search if we have this manga on database
                List<Manga> byUrl = mangaService.findByUrl(urlElement);
                if (byUrl.size() > 0) {
                    // get the last chapter of this MANGA
                    Manga manga = byUrl.get(0);
                    Page<Chapter> lastSaveChapter = this.chapterService.findLastChapter(manga.getId());
                    double lastSaveChapterNum =
                            lastSaveChapter.getTotalElements() > 0 ? lastSaveChapter.getContent().get(0).getNum() : 1;

                    // if we have it we have need to found the last chapter
                    final double finalLastSaveChapterNum = lastSaveChapterNum;
                    Set<Double> newChaptersNumber = element.getElementsByTag("dd").stream()
                            .map(element1 -> Double.parseDouble(element1.getElementsByTag("a").text().split("-")[1]))
                            .filter(aDouble -> aDouble > finalLastSaveChapterNum)
                            .collect(Collectors.toSet());
                    if(!newChaptersNumber.isEmpty()) {
                        // we get all number we can of chapter
                        Double lastNewChapterNumber = newChaptersNumber.stream().max(Double::compareTo).get();
                        while (lastSaveChapterNum < lastNewChapterNumber) {
                            newChaptersNumber.add(lastNewChapterNumber);
                            log.info("COUCOU ={}", lastNewChapterNumber);
                            lastSaveChapterNum++;
                        }

                        //we create all these chapter
                        newChaptersNumber.forEach(aDouble -> {
                            ChapterDto chapterDto = new ChapterDto(manga, aDouble);
                            try {
                                newChapters.add(chapter(chapterDto));
                            } catch (ChapterNotOutException e) {
                                log.error("This chapter was not found.={} ", chapterDto, e);
                            }
                        });
                    }
                }
            });
            return newChapters;
        } catch (final Exception e) {
            log.error("can't scrap the url ={}", MANGADOOM_URL, e);
            throw e;
        }
    }


    public ChapterDto chapter(ChapterDto chapter) throws ChapterNotOutException {
        double chapterNum = chapter.getChapterNumber();
        final String url = chapter.getManga().getUrl();
        String format = String.format("%s/%f/all-pages", url, chapterNum);
        if (chapterNum % 1 == 0) {
            format = String.format("%s/%d/all-pages", url, Double.valueOf(chapterNum).intValue());
        }
        String bookNameWithoutSpecialChar = chapter.getFilePath();
        try {
            final Document doc = addInfo(Jsoup.connect(format)).get();
            Element manga_updates = doc.getElementsByClass("content-inner inner-page").get(0);
            Elements images = manga_updates.getElementsByClass("img-responsive");
            List<File> files = new ArrayList<>();
            AtomicInteger page = new AtomicInteger(0);

            if (images.size() > 1) {
                images.forEach(element -> {
                    String imageUrl = element.absUrl("src");
                    try {
                        File file = fileCreationService.createFile(bookNameWithoutSpecialChar,
                                String.format("image_%s_%d.jpg", chapterNum, page.getAndIncrement()));
                        URL url1 = new URL(imageUrl);
                        FileUtils.copyURLToFile(url1, file);
                        files.add(file);
                    } catch (IOException e1) {
                        log.error("can't get the Image from url ={}", imageUrl);
                    }
                });

                chapter.setFile(files);
                return chapter;
            } else {
                throw new ChapterNotOutException("Chapter no found", chapter);
            }
        } catch (IOException e1) {
            log.error("Error during Creation of the file for chapter = {]", chapter, e1);
            return null;
        }
    }

}

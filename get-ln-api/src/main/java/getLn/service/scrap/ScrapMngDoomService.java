package getLn.service.scrap;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import getLn.model.ChapterDto;
import getLn.service.FileCreationService;
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

    /**
     * Get all manga from the news page
     *
     * @param i
     * @param chapter
     * @param url
     * @return
     * @throws IOException
     */
    private void scanLastScanOutPage(final int i, final ChapterDto chapter, final String url)
            throws IOException {
        final String format = String.format(url, i);
        try {
            Connection connect = Jsoup.connect(format);
            final Document doc = addInfo(connect).get();
            Element manga_updates = doc.getElementsByClass("manga_updates").get(0);
            Elements allManga = manga_updates.getElementsByTag("dl");
            allManga.forEach(element -> {
                //we get one line of last manga update
                Element elementTitle = element.getElementsByClass("manga-info-qtip").get(0);
                String urlElement = elementTitle.text().split("href=\"")[1].split("\" class")[0];
                //we search if we have this manga on database
                List<Manga> byUrl = this.mangaService.findByUrl(urlElement);
                if (byUrl.size() > 0) {
                    // get the last chapter of this MANGA
                    Manga manga = byUrl.get(0);
                    Chapter chapter1 = this.chapterService.findLastChapter(manga.getId()).getContent().get(0);

                    // if we have it we have need to found the last chapter
                    element.getElementsByTag("dd").forEach(element1 -> {
                        String a = element1.getElementsByTag("a").text();
                        String numberChapter = a.split("-")[1];
                        // we need to found
                        double number = Double.parseDouble(numberChapter);
                        if (number > chapter1.getNum()) {
                            Chapter chapter2 = new Chapter(manga);
                            chapter2.setNum(number);
                            //TODO SCAN THE chapter now

                        }
                    });
                }
            });
        } catch (final Exception e) {
            log.error("can't scrap the url ={}", format, e);
            throw e;
        }
    }


    public ChapterDto chapter(String url, Manga manga, double chapterNum, ChapterDto chapter) {
        //        "http://www.mngdoom.com/Tower-of-God/387;

        String format = String.format("%s/%f/all-pages", url, chapterNum);
        if (chapterNum % 1 == 0) {
            format = String.format("%s/%d/all-pages", url, Double.valueOf(chapterNum).intValue());
        }
        String bookNameWithoutSpecialChar = chapter.getFilePath();
        try {
            Connection connect = Jsoup.connect(format);
            final Document doc = addInfo(connect).get();
            Element manga_updates = doc.getElementsByClass("content-inner inner-page").get(0);
            Elements images = manga_updates.getElementsByClass("img-responsive");
            List<File> files = new ArrayList<>();
            AtomicInteger i = new AtomicInteger(0);

            images.forEach(element -> {
                String imageUrl = element.absUrl("src");
                try {
                    File file = fileCreationService.createFile(bookNameWithoutSpecialChar,
                            String.format("image_%s_%d.jpg", chapterNum, i.getAndIncrement()));
                    URL url1 = new URL(imageUrl);
                    FileUtils.copyURLToFile(url1, file);
                    files.add(file);
                } catch (IOException e1) {
                    log.error("can't get the Image from url ={}", imageUrl);
                }
            });

            chapter.setFile(files);
            return chapter;

        } catch (IOException e1) {
            log.error("EROOR");
            return null;
        }
    }

}

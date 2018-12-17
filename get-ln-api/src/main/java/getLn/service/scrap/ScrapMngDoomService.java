package getLn.service.scrap;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import getLn.model.ChapterDto;
import getln.data.entity.Chapter;
import getln.data.entity.Manga;
import getln.data.service.FilePersistenceService;
import getln.service.common.ChapterSqlService;
import getln.service.common.MangaSqlService;

/**
 * .
 */
@Service
public class ScrapMngDoomService extends ScrapService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScrapMngDoomService.class);

    @Inject
    private MangaSqlService mangaService;

    @Inject
    private ChapterSqlService chapterService;

    @Inject
    private FilePersistenceService filePersistenceService;

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
            LOGGER.error("can't scrap the url ={}", format, e);
            throw e;
        }
    }


    public ChapterDto chapter(String url, Manga manga, double chapterNum, ChapterDto chapter) {
        //        "http://www.mngdoom.com/Tower-of-God/387;
        final String format = String.format(url + "/all-pages");
        //        String mangaName = "towerOfGod";
        String bookNameWithoutSpecialChar = manga.getBookNameWithoutSpecialChar();
        try {
            Connection connect = Jsoup.connect(format);
            final Document doc = addInfo(connect).get();
            Element manga_updates = doc.getElementsByClass("content-inner inner-page").get(0);
            Elements images = manga_updates.getElementsByClass("img-responsive");
            List<File> files = new ArrayList<>();
            int[] i = new int[1];
            images.forEach(element -> {
                String imageUrl = element.absUrl("src");
                try {
                    File file = new File(bookNameWithoutSpecialChar + "/image_" + chapterNum + "_" + i + ".jpg");
                    URL url1 = new URL(imageUrl);
                    FileUtils.copyURLToFile(url1, file);
                    files.add(file);
                    i[0] = i[0]++;
                } catch (IOException e1) {
                    LOGGER.error("can't get the Image from url ={}", imageUrl);
                }
            });

            chapter.setFile(files);
            return chapter;

        } catch (IOException e1) {
            LOGGER.error("EROOR");
            return null;
        }
    }

}

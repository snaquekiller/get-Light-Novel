package getLn.service;

import static getln.data.entity.QChapter.chapter;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
import getln.data.entity.Manga;
import getln.service.common.MangaService;

/**
 * .
 */
@Service
public class ScrapMngDoom {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScrapMngDoom.class);

    @Inject
    private ScrapService scrapService;

    @Inject
    private MangaService mangaService;

    private ChapterDto addTextAndTitleMnDOOM(final int i, final ChapterDto chapter, final String url) throws IOException {
        final String format = String.format(url, i);
        try {
            Connection connect = Jsoup.connect(format);
            final Document doc = scrapService.addInfo(connect).get();
            Element manga_updates = doc.getElementsByClass("manga_updates").get(0);
            Elements dl = manga_updates.getElementsByTag("dl");
            dl.forEach(element -> {
                Element elementTitle = element.getElementsByClass("manga-info-qtip").get(0);
                String urlElement = elementTitle.text().split("href=\"")[1].split("\" class")[0];
                List<Manga> byUrl = mangaService.findByUrl(urlElement);
                if (byUrl.size() > 0) {
                    element.getElementsByTag("dd").forEach(element1 -> {
                        String a = element1.getElementsByTag("a").text();
                        String numberChapter = a.split("-")[1];
                    })
                }
            }); final List<String> textList =
                doc.getElementById("chapter-container").getElementsByClass("translated").stream().map(Element::text)
                    .collect(Collectors.toList());
            chapter.setName(chapterTitle);
            chapter.setTextList(textList);
            return chapter;
        } catch (final Exception e) {
            LOGGER.error("can't scrap the url ={}", format, e);
            throw e;
        }
    }

    public void chapter(String url, Manga manga, String chapterNum) {
        //        "http://www.mngdoom.com/Tower-of-God/387;
        final String format = String.format(url + "/all-pages");
        //        String mangaName = "towerOfGod";
        String bookNameWithoutSpecialChar = manga.getBookNameWithoutSpecialChar();
        try {
            Connection connect = Jsoup.connect(format);
            final Document doc = scrapService.addInfo(connect).get();
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
        } catch (IOException e1) {
            LOGGER.error("EROOR");
        }

        return chapter;
    }

}

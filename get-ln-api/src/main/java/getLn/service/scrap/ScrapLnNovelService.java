package getLn.service.scrap;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import getLn.model.ChapterDto;
import lombok.extern.slf4j.Slf4j;

/**
 * .
 */
@Slf4j
@Service
public class ScrapLnNovelService extends ScrapService {


    public ChapterDto addTextAndTitleLNMTL(final double chapterNumber, final ChapterDto chapter, final String url)
            throws IOException {
        final String format = String.format(url, chapterNumber);
        try {
            Connection connect = Jsoup.connect(format);
            final Document doc = addInfo(connect).get();
            final String chapterTitle = doc.getElementsByClass("chapter-title").get(0).text();
            final List<String> textList =
                    doc.getElementById("chapter-container").getElementsByClass("translated").stream().map(Element::text)
                            .collect(Collectors.toList());
            chapter.setName(chapterTitle);
            chapter.setTextList(textList);
            return chapter;
        } catch (final Exception e) {
            log.error("can't scrap the url ={}", format, e);
            throw e;
        }
    }
}

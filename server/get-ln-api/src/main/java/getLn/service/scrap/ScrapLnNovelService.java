package getLn.service.scrap;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import getLn.model.ChapterDto;

/**
 * .
 */
@Service
public class ScrapLnNovelService extends ScrapService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScrapMngDoomService.class);

    public ChapterDto addTextAndTitleLNMTL(final double i, final ChapterDto chapter, final String url)
            throws IOException {
        final String format = String.format(url, i);
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
            LOGGER.error("can't scrap the url ={}", format, e);
            throw e;
        }
    }
}

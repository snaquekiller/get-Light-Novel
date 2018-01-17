package getLn.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import getLn.model.ChapterDto;
import getln.data.Entity.Manga;

/**
 * .
 */
@Service
public class ScrapService {

    /** The logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ScanService.class);

    private static final String ligh_novel = "douluo-dalu-3-dragon-king-s-legend-chapter-";

    private static final String BOOK_NAME = "Douluo Dalu 3";

    public static final String UTF_8 = "utf-8";

    @Inject
    private ZipService zipService;

    public /*static */ void main() throws IOException {
        if (false) {
            String title = "";
            final List<ChapterDto> chapters = new ArrayList<ChapterDto>();
            for (int i = 1066; i < 1068; i++) {
                try {
                    final String bookWithoutSpecialChar = BOOK_NAME.replaceAll("\\W", "_");
                    final String fileName = String.format("%s_%d.xhtml", bookWithoutSpecialChar, i);
                    ChapterDto chapter = new ChapterDto(String.format("%s", bookWithoutSpecialChar), i, BOOK_NAME, fileName);
                    chapter = addTextAndTitle(i, chapter);
                    writeChapter(BOOK_NAME, chapter.getTextList(), chapter);
                    title += String.format("\t<li>\n\t<a href=\"%s#%d\">%s</a>\n</li>\n", fileName, i, chapter.getName());
                    chapters.add(chapter);
                } catch (final Exception e) {
                    System.err.println("Chapter  " + i + "not found ");
                }
            }
            createTableMatiere(BOOK_NAME, title);
            createTOX(BOOK_NAME, chapters);
        }
    }

    public ChapterDto scrapOne(final Manga manga, final int chapterNumber) {
        try {
            final String bookWithoutSpecialChar = manga.getBookNameWithoutSpecialChar();
            final String fileName = String.format("%s_%d.xhtml", bookWithoutSpecialChar, chapterNumber);
            final String bookName = manga.getName();
            ChapterDto chapter = new ChapterDto(String.format("%s", bookWithoutSpecialChar), chapterNumber, bookName, fileName);
            chapter = addTextAndTitle(chapterNumber, chapter);
            chapter.setFile(writeChapter(bookName, chapter.getTextList(), chapter));
            return chapter;
        } catch (final Exception e) {
            LOGGER.error("Chapter  " + chapterNumber + "not found ");
        }
        return null;
    }

    private ChapterDto addTextAndTitle(final int i, final ChapterDto chapter) throws IOException {
        final Document doc = Jsoup.connect("http://lnmtl.com/chapter/" + ligh_novel + i).get();
        final String chapterTitle = doc.getElementsByClass("chapter-title").get(0).text();
        final List<String> textList =
            doc.getElementById("chapter-container").getElementsByClass("translated").stream().map(Element::text)
                .collect(Collectors.toList());
        chapter.setName(chapterTitle);
        chapter.setTextList(textList);
        return chapter;
    }

    private void createTableMatiere(final String titleBook, final String list) {
        final String title = titleBook.replace(" ", "_");

        final String head =
            "<?xml version='1.0' encoding='utf-8'?>\n" + "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" + "  <head>\n" +
                "    <title>" + titleBook + "</title>\n" +
                "    <link href=\"../styles/stylesheet.css\" rel=\"stylesheet\" type=\"text/css\"/>\n" + "  </head>\n" +
                "  <body>\n" + "    <h1 class=\"center\" id=\"toc\">Table des Matières</h1>\n" + "    <ul>\n";

        final String end = "    </ul>\n" + "  </body>\n" + "</html>";

        Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(title + "/toc.xhtml"), "utf-8"));
            writer.write(head);
            writer.write(list);
            writer.write(end);
        } catch (final IOException ex) {
            // report
            LOGGER.error("Can't create the file");
        } finally {
            try {
                writer.close();
            } catch (final Exception ex) {/*ignore*/
            }
        }
    }

    private File writeChapter(final String titleBook, final List<String> textList, final ChapterDto chapter) {
        Writer writer = null;
        final String head =
            "<?xml version='1.0' encoding='utf-8'?>\n" + "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" + "\t<head>\n" +
                "\t<title>" + titleBook + "</title>\n" +
                "\t<link href=\"../styles/stylesheet.css\" rel=\"stylesheet\" type=\"text/css\"/>\n" + "\t</head>\n" +
                " \t\t<body>\n" + "\t\t<h1 class=\"center\" id=\"" + chapter.getChapterNumber() + "\">" + chapter.getName() +
                "</h2>\n";

        final String end = "  </body>\n" + "</html>";
        try {
            final String name = String.format("%s/%s", chapter.getFilePath(), chapter.getFileName());
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(name), "utf-8"));
            writer.write(head);
            String texte = "";
            for (final String text : textList) {
                texte = texte + "\n" + text;
                writer.write(String.format("<p>%s</p>\n\n", text));
            }
            writer.write(end);
            return new java.io.File(name);
        } catch (final IOException ex) {
            // report
            LOGGER.error("can't write" + ex);
        } finally {
            try {
                if (null != writer) {
                    writer.close();
                }
            } catch (final Exception ex) {/*ignore*/
                LOGGER.error("c'ant close" + ex);
            }
        }
        return null;
    }

    public void deleteOldChapter(final File file) {
        file.delete();
    }

    /**
     * Function for create the table of chapter
     *
     * @param titleBook the book name
     * @param chapters  all chapters you want list
     */
    private File createTOX(final String titleBook, final List<ChapterDto> chapters) {
        Writer writer = null;
        final String head = "<?xml version='1.0' encoding='utf-8'?>\n" + "<!DOCTYPE ncx PUBLIC \"-//NISO//DTD ncx 2005-1//EN\"" +
            " \"http://www.daisy.org/z3986/2005/ncx-2005-1.dtd\"><ncx version=\"2005-1\" xmlns=\"http://www.daisy.org/z3986/2005/ncx/\">\n" +
            "\t<head>\n" + "\t\t<meta content=\"urn:uuid:39100132-78e1-4817-a9d5-7185c795bfba\" name=\"dtb:uid\"/>\n" +
            "\t\t<meta content=\"1\" name=\"dtb:depth\"/>\n" + "\t\t<meta content=\"0\" name=\"dtb:totalPageCount\"/>\n" +
            "\t\t<meta content=\"0\" name=\"dtb:maxPageNumber\"/>\n" + "\t</head>\n" + "\t<docTitle>\n" + "\t\t<text>" +
            titleBook + "</text>\n" + "\t</docTitle>\n\t<navMap>";
        try {
            final String name = "tox.ncx";
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(name), UTF_8));
            writer.write(head);
            for (int i = 0; i < chapters.size(); i++) {
                final ChapterDto chapter = chapters.get(i);
                writer.write(String.format(
                    "<navPoint id=\"navPoint-%d\" playOrder=\"%d\">\n<navLabel>\n<text>%s</text>\n</navLabel>\n<content src=\"Text/%s\"/>\n</navPoint>",
                    i, i, chapter.getName(), chapter.getFileName()));
            }
            writer.write("</navMap>\n" + "</ncx>");
            return new File(name);
        } catch (final IOException ex) {
            // report
            LOGGER.error("can't write" + ex);
        } finally {
            try {
                if (null != writer) {
                    writer.close();
                }
            } catch (final Exception ex) {/*ignore*/
                LOGGER.error("c'ant close" + ex);
            }
        }
        return null;
    }
}

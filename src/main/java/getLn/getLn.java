package getLn;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

import getLn.configuration.GetLnConfiguration;
import getLn.model.Chapter;

/**
 * @author Nicolas
 */
@Configuration
@Import({GetLnConfiguration.class})
@SpringBootApplication
@EnableScheduling
public class getLn {

    private static final String ligh_novel = "douluo-dalu-3-dragon-king-s-legend-chapter-";

    private static final String BOOK_NAME = "Douluo Dalu 3";

    /**
     * @param args the command line arguments
     */
    public static void main(final String[] args) throws IOException {
        SpringApplication.run(getLn.class);
        if (false) {
            String title = "";
            final List<Chapter> chapters = new ArrayList<Chapter>();
            for (int i = 1066; i < 1068; i++) {
                try {

                    final String bookWithoutSpecialChar = BOOK_NAME.replaceAll("\\W", "_");
                    final String fileName = String.format("%s_%d.xhtml", bookWithoutSpecialChar, i);
                    Chapter chapter = new Chapter(String.format("%s", bookWithoutSpecialChar), i, BOOK_NAME, fileName);
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

    private static Chapter addTextAndTitle(final int i, final Chapter chapter) throws IOException {
        final Document doc = Jsoup.connect("http://lnmtl.com/chapter/" + ligh_novel + i).get();
        final String chapterTitle = doc.getElementsByClass("chapter-title").get(0).text();
        System.out.println(chapterTitle);
        final List<String> textList =
            doc.getElementById("chapter-container").getElementsByClass("translated").stream().map(Element::text)
                .collect(Collectors.toList());
        chapter.setName(chapterTitle);
        chapter.setTextList(textList);
        return chapter;
    }

    private static void createTableMatiere(final String titleBook, final String list) {
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
        } finally {
            try {
                writer.close();
            } catch (final Exception ex) {/*ignore*/
            }
        }
    }

    private static void writeChapter(final String titleBook, final List<String> textList, final Chapter chapter) {
        Writer writer = null;
        final String head =
            "<?xml version='1.0' encoding='utf-8'?>\n" + "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" + "\t<head>\n" +
                "\t<title>" + titleBook + "</title>\n" +
                "\t<link href=\"../styles/stylesheet.css\" rel=\"stylesheet\" type=\"text/css\"/>\n" + "\t</head>\n" +
                " \t\t<body>\n" + "\t\t<h1 class=\"center\" id=\"" + chapter.getChapterNumber() + "\">" + chapter.getName() +
                "</h2>\n";

        final String end = "  </body>\n" + "</html>";
        try {
            writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(String.format("%s/%s", chapter.getFilePath(), chapter.getFileName())),
                    "utf-8"));
            writer.write(head);
            for (final String text : textList) {
                writer.write(String.format("<p>%s</p>\n\n", text));
            }
            writer.write(end);
        } catch (final IOException ex) {
            // report
            System.err.println("can't write" + ex);
        } finally {
            try {
                if (null != writer) {
                    writer.close();
                }
            } catch (final Exception ex) {/*ignore*/
                System.err.println("c'ant close" + ex);
            }
        }
    }

    private static void createTOX(final String titleBook, final List<Chapter> chapters) {
        Writer writer = null;
        final String head = "<?xml version='1.0' encoding='utf-8'?>\n" + "<!DOCTYPE ncx PUBLIC \"-//NISO//DTD ncx 2005-1//EN\"" +
            " \"http://www.daisy.org/z3986/2005/ncx-2005-1.dtd\"><ncx version=\"2005-1\" xmlns=\"http://www.daisy.org/z3986/2005/ncx/\">\n" +
            "\t<head>\n" + "\t\t<meta content=\"urn:uuid:39100132-78e1-4817-a9d5-7185c795bfba\" name=\"dtb:uid\"/>\n" +
            "\t\t<meta content=\"1\" name=\"dtb:depth\"/>\n" + "\t\t<meta content=\"0\" name=\"dtb:totalPageCount\"/>\n" +
            "\t\t<meta content=\"0\" name=\"dtb:maxPageNumber\"/>\n" + "\t</head>\n" + "\t<docTitle>\n" + "\t\t<text>" +
            titleBook + "</text>\n" + "\t</docTitle>\n\t<navMap>";
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("tox.ncx"), "utf-8"));
            writer.write(head);
            for (int i = 0; i < chapters.size(); i++) {
                final Chapter chapter = chapters.get(i);
                writer.write(String.format(
                    "<navPoint id=\"navPoint-%d\" playOrder=\"%d\">\n<navLabel>\n<text>%s</text>\n</navLabel>\n<content src=\"Text/%s\"/>\n</navPoint>",
                    i, i, chapter.getName(), chapter.getFileName()));
            }
            writer.write("</navMap>\n" + "</ncx>");
        } catch (final IOException ex) {
            // report
            System.err.println("can't write" + ex);
        } finally {
            try {
                if (null != writer) {
                    writer.close();
                }
            } catch (final Exception ex) {/*ignore*/
                System.err.println("c'ant close" + ex);
            }
        }
    }

}

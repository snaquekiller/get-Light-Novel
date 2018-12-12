package getLn.service.ebook;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import getLn.model.ChapterDto;

/**
 * .
 */
@Service
public class EpubService {

    /**
     * The logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(EpubService.class);

    public static final String UTF_8 = "utf-8";

    public static final String DIRECTORY = "data";

    private static final String DATE_FORMAT = "dd/MM/yyyy HH:mm";

    private final SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);

    /**
     * The serial version UID.
     */
    private static final long serialVersionUID = 1L;

    @Inject
    private ZipService zipService;


    public List<File> createOpfFile(final List<File> files, final ChapterDto chapter) {
        //@formatter:off
        final String head = "<?xml version='1.0' encoding='utf-8'?>\n" +
                "<package xmlns=\"http://www.idpf.org/2007/opf\" unique-identifier=\"uuid_id\" version=\"2.0\">\n" +
                "  <metadata xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:opf=\"http://www.idpf.org/2007/opf\" xmlns:dcterms=\"http://purl.org/dc/terms/\" xmlns:calibre=\"http://calibre.kovidgoyal.net/2009/metadata\" xmlns:dc=\"http://purl.org/dc/elements/1.1/\">\n" +
                "    <meta name=\"calibre:title_sort\" content=\"\"/>\n" +
                "    <dc:language>en</dc:language>\n" +
                "    <dc:creator opf:file-as=\"Vert\" opf:role=\"aut\">Vert</dc:creator>\n" +
                "    <meta name=\"calibre:timestamp\" content=\" " + this.format.format(new Date()) + "\"/>\n" +
                "    <dc:title>" + chapter.getBookName() + " : " + chapter.getChapterNumber() + "</dc:title>\n" +
                "    <meta name=\"cover\" content=\"cover\"/>\n" +
                "    <dc:date>" + this.format.format(new Date()) + "</dc:date>\n" +
                "    <dc:contributor opf:role=\"bkp\">snaquekiller</dc:contributor>\n" +
                "    <dc:identifier id=\"uuid_id\" opf:scheme=\"uuid\">" + serialVersionUID + "</dc:identifier>\n" +
                "    <dc:identifier opf:scheme=\"calibre\">" + serialVersionUID + "</dc:identifier>\n" +
                "  </metadata>\n" +
                "<manifest>";

        final String end = "  \n"
                + "</package>\n";
        //@formatter:on

        Writer writer = null;

        final String name = String.format("%s/book.opf", chapter.getFilePath());
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(name), "utf-8"));
            writer.write(head);
            String spine = "<spine toc=\"ncx\">\n";
            String manifest = "<manifest>\n";
            for (final File file : files) {
                manifest += String
                        .format("<item id=\"%s\" href=\"%s\" media-type=\"application/xhtml+xml\">\n", file.getName(),
                                file.getName());
                spine += String.format("<itemref idref=\"%s\"/>", file.getName());
            }
            manifest += "</manifest>\n";
            spine += "</spine>\n";
            writer.write(manifest);
            writer.write(spine);
            writer.write(end);
        } catch (final IOException ex) {
            // report
            LOGGER.error("Can't create the opf file={}", name, ex);
        } finally {
            try {
                writer.close();
            } catch (final Exception ex) {/*ignore*/
            }
        }
        final File e = new File(name);
        final List<File> filess = new ArrayList<>(files);
        filess.add(e);
        return filess;
    }

    public void createTableMatiere(final String titleBook, final String list) {
        final String title = titleBook.replace(" ", "_");

        final String head =
                "<?xml version='1.0' encoding='utf-8'?>\n" + "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n"
                        + "  <head>\n" +
                        "    <title>" + titleBook + "</title>\n" +
                        "    <link href=\"../styles/stylesheet.css\" rel=\"stylesheet\" type=\"text/css\"/>\n"
                        + "  </head>\n" +
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
            LOGGER.error("Can't create the file", ex);
        } finally {
            try {
                writer.close();
            } catch (final Exception ex) {/*ignore*/
            }
        }
    }

    public File writeChapter(final String titleBook, final List<String> textList, final ChapterDto chapter) {
        Writer writer = null;
        final String head =
                "<?xml version='1.0' encoding='utf-8'?>\n" + "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n"
                        + "\t<head>\n" +
                        "\t<title>" + titleBook + "</title>\n" +
                        "\t<link href=\"../styles/stylesheet.css\" rel=\"stylesheet\" type=\"text/css\"/>\n"
                        + "\t</head>\n" +
                        " \t\t<body>\n" + "\t\t<h1 class=\"center\" id=\"" + chapter.getChapterNumber() + "\">"
                        + chapter.getName() +
                        "</h1>\n";

        final String end = "  </body>\n" + "</html>";
        try {
            final String name = String.format("%s/%s", chapter.getFilePath(), chapter.getFileName());

            final File file = new File(name);
            final File directory = new File(String.format("%s", chapter.getFilePath()));
            directory.mkdirs();

            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
            writer.write(head);
            String texte = "";
            for (final String text : textList) {
                texte = texte + "\n" + text;
                writer.write(String.format("<p>%s</p>\n\n", text));
            }
            writer.write(end);
            return file;
        } catch (final IOException ex) {
            // report
            LOGGER.error("can't write", ex);
        } finally {
            try {
                if (null != writer) {
                    writer.close();
                }
            } catch (final Exception ex) {/*ignore*/
                LOGGER.error("can't close" + ex);
            }
        }
        return null;
    }

    /**
     * Function for create the table of chapter
     *
     * @param titleBook the book name
     * @param chapters  all chapters you want list
     */
    public File createTOX(final String titleBook, final List<ChapterDto> chapters) {
        Writer writer = null;
        final String head =
                "<?xml version='1.0' encoding='utf-8'?>\n" + "<!DOCTYPE ncx PUBLIC \"-//NISO//DTD ncx 2005-1//EN\"" +
                        " \"http://www.daisy.org/z3986/2005/ncx-2005-1.dtd\"><ncx version=\"2005-1\" xmlns=\"http://www.daisy.org/z3986/2005/ncx/\">\n"
                        +
                        "\t<head>\n"
                        + "\t\t<meta content=\"urn:uuid:39100132-78e1-4817-a9d5-7185c795bfba\" name=\"dtb:uid\"/>\n" +
                        "\t\t<meta content=\"1\" name=\"dtb:depth\"/>\n"
                        + "\t\t<meta content=\"0\" name=\"dtb:totalPageCount\"/>\n" +
                        "\t\t<meta content=\"0\" name=\"dtb:maxPageNumber\"/>\n" + "\t</head>\n" + "\t<docTitle>\n"
                        + "\t\t<text>" +
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

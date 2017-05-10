/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package getln;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Nicolas
 */
public class GetLn {

    private static String ligh_novel = "douluo-dalu-3-dragon-king-s-legend-chapter-";

    private static String BOOK_NAME = "Douluo Dalu 3";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        //launch(args);
        String Title = "";
        for (int i = 761; i < 906; i++) {
            try {
                Document doc = Jsoup.connect("http://lnmtl.com/chapter/" + ligh_novel + i).get();

                Elements title = doc.getElementsByClass("chapter-title");
                System.out.println(title.get(0).text());
                Element el = doc.getElementById("chapter-container");

                Elements dd = el.getElementsByClass("translated");
                Title += createBook(BOOK_NAME, dd, title.get(0).text(), i + "") + "\n";
            } catch (Exception e) {
                System.err.println("Chapter  " + i + "not found ");
            }
        }
        createTableMatiere(BOOK_NAME, Title);

    }

    public static void createTableMatiere(String titleBook, String list) {

        String title = titleBook.replace(" ", "_");

        String head = "<?xml version='1.0' encoding='utf-8'?>\n"
                + "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n"
                + "  <head>\n"
                + "    <title>" + titleBook + "</title>\n"
                + "    <link href=\"../styles/stylesheet.css\" rel=\"stylesheet\" type=\"text/css\"/>\n"
                + "  </head>\n"
                + "  <body>\n"
                + "    <h1 class=\"center\" id=\"toc\">Table des Matières</h1>\n"
                + "    <ul>\n";

        String end = "    </ul>\n"
                + "  </body>\n"
                + "</html>";

        Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(title + "/toc.xhtml"), "utf-8"));
            writer.write(head);
            writer.write(list);
            writer.write(end);
        } catch (IOException ex) {
            // report
        } finally {
            try {
                writer.close();
            } catch (Exception ex) {/*ignore*/
            }
        }

    }

    public static String createBook(String titleBook, Elements elements, String ChapterTitle, String num) {
        Writer writer = null;
        String head = "<?xml version='1.0' encoding='utf-8'?>\n"
                + "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n"
                + "  <head>\n"
                + "    <title>" + titleBook + "</title>\n"
                + "    <link href=\"../styles/stylesheet.css\" rel=\"stylesheet\" type=\"text/css\"/>\n"
                + "  </head>\n"
                + "  <body>\n"
                + "    <h1 class=\"center\" id=\"" + num + "\">" + ChapterTitle + "</h2>\n";

        String end = "  </body>\n"
                + "</html>";
        String title = titleBook.replace(" ", "_");
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(title + "/" + title + "_" + num + ".xhtml"), "utf-8"));
            writer.write(head);
            for (int i = 0; i < elements.size(); i++) {
                writer.write("<p>" + elements.get(i).text() + "</p>\n\n");

            }
            writer.write(end);
        } catch (IOException ex) {
            // report
            System.err.println("c'ant write" + ex);
        } finally {
            try {
                writer.close();
            } catch (Exception ex) {/*ignore*/
                System.err.println("c'ant close" + ex);
            }
        }
        String list = "      <li>\n"
                + "        <a href=\"" + title + "_" + num + ".xhtml#" + num + "\">" + ChapterTitle + "</a>\n"
                + "      </li>\n";
        return list;
    }
}

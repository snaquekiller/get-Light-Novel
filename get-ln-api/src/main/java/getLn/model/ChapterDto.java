package getLn.model;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import getln.data.entity.Manga;

/**
 * The chapter of a book
 */
@lombok.Data
public class ChapterDto implements Serializable {

    /**
     * The chapter name
     */
    private String name;

    /**
     * The Chapter FileName
     */
    private String filePath;

    /**
     * The chapter Number
     */
    private double chapterNumber;


    private String fileName;

    /**
     * The list that contains all the text of the chapter
     **/
    private List<String> textList;

    private List<File> file;

    private  Manga manga;

    /**
     * The text of the
     */
    private String text;

    public ChapterDto(final String filePath, final double chapterNumber, final String bookName, final String fileName) {
        this.filePath = filePath;
        this.chapterNumber = chapterNumber;
        this.fileName = fileName;
    }

    public ChapterDto(Manga manga, final double chapterNumber) {
        String bookNameWithoutSpecialChar = manga.getBookNameWithoutSpecialChar();
        this.filePath = String.format("%s/%s", bookNameWithoutSpecialChar, chapterNumber);
        this.chapterNumber = chapterNumber;
        String fileName = String.format("%s_%f.xhtml", bookNameWithoutSpecialChar, chapterNumber);
        if (chapterNumber % 1 == 0) {
            fileName = String
                    .format("%s_%d.xhtml", bookNameWithoutSpecialChar, Double.valueOf(chapterNumber).intValue());
        }
        this.fileName = fileName;
        this.manga = manga;
    }


    /**
     * Sets new file.
     *
     * @param file New value of file.
     */
    public void setFile(final List<File> file) {
        this.file = file;
    }

    /**
     * Gets file.
     *
     * @return Value of file.
     */
    public List<File> getFile() {
        return file;
    }
}

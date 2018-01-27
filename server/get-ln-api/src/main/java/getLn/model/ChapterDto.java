package getLn.model;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * The chapter of a book
 */
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
    private int chapterNumber;

    /**
     * The bookName associate of the chapter
     **/
    private String bookName;

    private String fileName;

    /**
     * The list that contains all the text of the chapter
     **/
    private List<String> textList;

    public File getFile() {
        return file;
    }

    public void setFile(final File file) {
        this.file = file;
    }

    private File file;

    /**
     * The text of the
     */
    private String text;

    public ChapterDto(final String filePath, final int chapterNumber, final String bookName, final String fileName) {
        this.filePath = filePath;
        this.chapterNumber = chapterNumber;
        this.bookName = bookName;
        this.fileName = fileName;
    }

    /**
     * Gets The chapter name.
     *
     * @return Value of The chapter name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets new The chapter name.
     *
     * @param name New value of The chapter name.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets The text of the.
     *
     * @return Value of The text of the.
     */
    public String getText() {
        return text;
    }

    /**
     * Sets new The text of the.
     *
     * @param text New value of The text of the.
     */
    public void setText(final String text) {
        this.text = text;
    }

    /**
     * Gets The chapter Number.
     *
     * @return Value of The chapter Number.
     */
    public int getChapterNumber() {
        return chapterNumber;
    }

    /**
     * Sets new The chapter Number.
     *
     * @param chapterNumber New value of The chapter Number.
     */
    public void setChapterNumber(final int chapterNumber) {
        this.chapterNumber = chapterNumber;
    }

    /**
     * Gets The Chapter FileName.
     *
     * @return Value of The Chapter FileName.
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Sets new The Chapter FileName.
     *
     * @param filePath New value of The Chapter FileName.
     */
    public void setFilePath(final String filePath) {
        this.filePath = filePath;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final ChapterDto that = (ChapterDto) o;

        return Objects.equals(this.name, that.name) && Objects.equals(this.filePath, that.filePath) &&
                Objects.equals(this.chapterNumber, that.chapterNumber) && Objects.equals(this.bookName, that.bookName) &&
                Objects.equals(this.fileName, that.fileName) && Objects.equals(this.text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, filePath, chapterNumber, bookName, fileName, text);
    }

    /**
     * Gets The bookName associate of the chapter.
     *
     * @return Value of The bookName associate of the chapter.
     */
    public String getBookName() {
        return bookName;
    }

    /**
     * Sets new The bookName associate of the chapter.
     *
     * @param bookName New value of The bookName associate of the chapter.
     */
    public void setBookName(final String bookName) {
        this.bookName = bookName;
    }

    /**
     * Gets fileName.
     *
     * @return Value of fileName.
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Sets new fileName.
     *
     * @param fileName New value of fileName.
     */
    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }

    /**
     * Sets new The list that contains all the text of the chapter.
     *
     * @param textList New value of The list that contains all the text of the chapter.
     */
    public void setTextList(final List<String> textList) {
        this.textList = textList;
    }

    /**
     * Gets The list that contains all the text of the chapter.
     *
     * @return Value of The list that contains all the text of the chapter.
     */
    public List<String> getTextList() {
        return textList;
    }
}

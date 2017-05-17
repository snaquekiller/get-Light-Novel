package getLn;

import java.util.Objects;

/**
 * The chapter of a book
 */
public class Chapter {

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

    /** The bookName associate of the chapter **/
    private String bookName;

    private String fileName;

    /**
     * The text of the
     */
    private String text;

    public Chapter(String name, String filePath, int chapterNumber, String bookName, String fileName) {
        this.name = name;
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
    public void setName(String name) {
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
    public void setText(String text) {
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
    public void setChapterNumber(int chapterNumber) {
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
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Chapter that = (Chapter) o;

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
    public void setBookName(String bookName) {
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
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}

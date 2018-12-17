package getLn.model;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

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

    /**
     * The bookName associate of the chapter
     **/
    private String bookName;

    private String fileName;

    /**
     * The list that contains all the text of the chapter
     **/
    private List<String> textList;

    private List<File> file;

    /**
     * The text of the
     */
    private String text;

    public ChapterDto(final String filePath, final double chapterNumber, final String bookName, final String fileName) {
        this.filePath = filePath;
        this.chapterNumber = chapterNumber;
        this.bookName = bookName;
        this.fileName = fileName;
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

    @Override
    public String toString() {
        return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]").add("name = " + name)
            .add("filePath = " + filePath).add("chapterNumber = " + chapterNumber).add("bookName = " + bookName)
            .add("fileName = " + fileName).add("file = " + file).add("text = " + text).toString();
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

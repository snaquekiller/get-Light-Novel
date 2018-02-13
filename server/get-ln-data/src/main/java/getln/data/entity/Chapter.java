package getln.data.entity;

import java.util.Set;
import java.util.StringJoiner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import getln.data.commons.AbstractDeletableJpaEntity;

/**
 * .
 */
@Entity
@Table(name = "chapter", schema = "LN")
public class Chapter extends AbstractDeletableJpaEntity<Long> {

    @Column(name = "title")
    private String title;

    @Column
    private String text;

    @Column
    private int num = 0;

    @Column
    private String tome;

    /**
     * The manga associate of the chapter
     */
    @ManyToOne
    @JoinColumn(name = "manga_id")
    private Manga manga;

    /**
     * All file for each chapter
     */
    //@formatter:off
    @OneToMany
    @JoinTable(schema = "LN", name = "chapter_files",
        joinColumns = @JoinColumn(name = "chapter_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "file_id", referencedColumnName = "id")
    )
    //@formatter:on
    private Set<FileStorage> files;

    //    @JoinColumn(name = "file_id")
    //    @OneToOne
    //    private File file;

    public Chapter() {
    }

    public Chapter(final String text, final int num, final String title, final String tome, final Set<FileStorage> files) {
        this.text = text;
        this.num = num;
        this.title = title;
        this.tome = tome;
        this.files = files;
    }

    /**
     * Gets title.
     *
     * @return Value of title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets new title.
     *
     * @param title New value of title.
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * Gets num.
     *
     * @return Value of num.
     */
    public int getNum() {
        return num;
    }

    /**
     * Sets new num.
     *
     * @param num New value of num.
     */
    public void setNum(final int num) {
        this.num = num;
    }

    /**
     * Gets Tome.
     *
     * @return Value of Tome.
     */
    public String getTome() {
        return tome;
    }

    /**
     * Sets new Tome.
     *
     * @param Tome New value of Tome.
     */
    public void setTome(final String Tome) {
        this.tome = Tome;
    }

    /**
     * Gets text.
     *
     * @return Value of text.
     */
    public String getText() {
        return text;
    }

    /**
     * Gets The manga associate of the chapter.
     *
     * @return Value of The manga associate of the chapter.
     */
    public Manga getManga() {
        return manga;
    }

    /**
     * Sets new text.
     *
     * @param text New value of text.
     */
    public void setText(final String text) {
        this.text = text;
    }

    /**
     * Sets new The manga associate of the chapter.
     *
     * @param manga New value of The manga associate of the chapter.
     */
    public void setManga(final Manga manga) {
        this.manga = manga;
    }

    /**
     * Gets All file for each chapter.
     *
     * @return Value of All file for each chapter.
     */
    public Set<FileStorage> getFiles() {
        return files;
    }

    /**
     * Sets new All file for each chapter.
     *
     * @param files New value of All file for each chapter.
     */
    public void setFiles(final Set<FileStorage> files) {
        this.files = files;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]").add("title = " + title).add("text = " + text)
            .add("num = " + num).add("tome = " + tome).add("manga = " + manga).add("files = " + files)
            .add("creationDate = " + creationDate).add("updateDate = " + updateDate).add("deleted = " + deleted).add("id = " + id)
            .toString();
    }
}

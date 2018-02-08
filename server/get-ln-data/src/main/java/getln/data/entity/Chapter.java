package getln.data.entity;

import java.util.StringJoiner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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

    @ManyToOne
    @JoinColumn(name = "manga_id")
    private Manga manga;

    @JoinColumn(name = "file_id")
    @OneToOne
    private File file;

    public Chapter() {
    }

    public Chapter(final String text, final int num, final String title, final String tome, final File file) {
        this.text = text;
        this.num = num;
        this.title = title;
        this.tome = tome;
        this.file = file;
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
     * Gets texte.
     *
     * @return Value of texte.
     */
    public String getTexte() {
        return text;
    }

    /**
     * Sets new texte.
     *
     * @param texte New value of texte.
     */
    public void setTexte(final String texte) {
        this.text = texte;
    }

    /**
     * Gets file.
     *
     * @return Value of file.
     */
    public File getFile() {
        return file;
    }

    /**
     * Sets new file.
     *
     * @param file New value of file.
     */
    public void setFile(final File file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]").add("texte = " + text).add("num = " + num)
            .add("title = " + title).add("Tome = " + tome).add("file = " + file).add("creationDate = " + creationDate)
            .add("deletionDate = " + updateDate).add("deleted = " + deleted).add("id = " + id).toString();
    }
}

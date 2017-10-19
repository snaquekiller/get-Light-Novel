package get.ln.data;

import java.util.StringJoiner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * .
 */
@Entity
@Table(name = "chapter", schema = "LN")
public class Chapter extends AbstractDeletableJpaEntity<Long> {

    @Column(name = "name")
    private String name;

    private String texte;

    private int num = 0;

    private String title;

    private String Tome;

    @JoinColumn(name = "profile_picture")
    @OneToOne
    private File file;

    public Chapter() {
    }

    public Chapter(final String name, final String texte, final int num, final String title, final String tome, final File file) {
        this.name = name;
        this.texte = texte;
        this.num = num;
        this.title = title;
        Tome = tome;
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
        return Tome;
    }

    /**
     * Sets new Tome.
     *
     * @param Tome New value of Tome.
     */
    public void setTome(final String Tome) {
        this.Tome = Tome;
    }

    /**
     * Gets name.
     *
     * @return Value of name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets new name.
     *
     * @param name New value of name.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets texte.
     *
     * @return Value of texte.
     */
    public String getTexte() {
        return texte;
    }

    /**
     * Sets new texte.
     *
     * @param texte New value of texte.
     */
    public void setTexte(final String texte) {
        this.texte = texte;
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
        return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]").add("name = " + name).add("texte = " + texte)
            .add("num = " + num).add("title = " + title).add("Tome = " + Tome).add("file = " + file)
            .add("creationDate = " + creationDate).add("deletionDate = " + updateDate).add("deleted = " + deleted)
            .add("id = " + id).toString();
    }
}

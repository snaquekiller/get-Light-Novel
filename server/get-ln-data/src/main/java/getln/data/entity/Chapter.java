package getln.data.entity;

import java.util.Set;
import java.util.StringJoiner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@lombok.Data
public class Chapter extends AbstractDeletableJpaEntity<Long> {

    @Column(name = "title")
    private String title;

    @Column
    private String text;

    @Column
    private double num = 0;

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
    @OneToMany(fetch = FetchType.EAGER)
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

    public Chapter(final String text, final double num, final String title, final String tome, final Set<FileStorage> files) {
        this.text = text;
        this.num = num;
        this.title = title;
        this.tome = tome;
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

package getln.data.entity;

import java.util.StringJoiner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import getln.data.commons.AbstractDeletableJpaEntity;

/**
 * .
 */
@Entity
@Table(name = "manga", schema = "LN")
@lombok.Data
public class Manga extends AbstractDeletableJpaEntity<Long> {

    /**
     * The name of the of the manga
     */
    @Column
    private String name;

    /**
     * The author of the manga
     */
    @Column
    private String author;

    /**
     * IF you have a comment
     */
    @Column
    private String comment;

    /**
     * The url where you can scrap the website
     */
    @Column
    private String url;

    /**
     * BookType
     */
    @Enumerated(EnumType.STRING)
    private BOOK_TYPE type;

    public Manga() {
    }

    public Manga(final String name, final String author, final String comment, final String url, final BOOK_TYPE type) {
        this.name = name;
        this.author = author;
        this.comment = comment;
        this.url = url;
        this.type = type;
    }

    public String getBookNameWithoutSpecialChar() {
        return name.replaceAll("\\W", "_");
    }

    public String getChapterFileName(final int chapterNumber) {
        final String bookWithoutSpecialChar = getBookNameWithoutSpecialChar();
        return String.format("%s_%d.xhtml", bookWithoutSpecialChar, chapterNumber);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]").add("name = " + name).add("author = " + author)
            .add("comment = " + comment).add("url = " + url).add("type = " + type).add("id = " + id).toString();
    }
}

package get.ln.data;

import java.util.StringJoiner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import get.ln.data.commons.AbstractDeletableJpaEntity;

/**
 * .
 */
@Entity
@Table(name = "manga", schema = "LN")
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
    private String Comment;

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
        Comment = comment;
        this.url = url;
        this.type = type;
    }

    /**
     * Gets The url where you can scrap the website.
     *
     * @return Value of The url where you can scrap the website.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets new The url where you can scrap the website.
     *
     * @param url New value of The url where you can scrap the website.
     */
    public void setUrl(final String url) {
        this.url = url;
    }

    /**
     * Gets The name of the of the manga.
     *
     * @return Value of The name of the of the manga.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets new The name of the of the manga.
     *
     * @param name New value of The name of the of the manga.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets The author of the manga.
     *
     * @return Value of The author of the manga.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets new The author of the manga.
     *
     * @param author New value of The author of the manga.
     */
    public void setAuthor(final String author) {
        this.author = author;
    }

    /**
     * Gets IF you have a comment.
     *
     * @return Value of IF you have a comment.
     */
    public String getComment() {
        return Comment;
    }

    /**
     * Sets new IF you have a comment.
     *
     * @param Comment New value of IF you have a comment.
     */
    public void setComment(final String Comment) {
        this.Comment = Comment;
    }

    /**
     * Gets BookType.
     *
     * @return Value of BookType.
     */
    public BOOK_TYPE getType() {
        return type;
    }

    /**
     * Sets new BookType.
     *
     * @param type New value of BookType.
     */
    public void setType(final BOOK_TYPE type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]").add("name = " + name).add("author = " + author)
            .add("Comment = " + Comment).add("url = " + url).add("type = " + type).add("id = " + id).toString();
    }
}

package getLn.model.request;

import getln.data.Entity.BOOK_TYPE;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;


public class MangaRequestDto implements Serializable {
    /**
     * The name of the of the manga
     */
    @NotBlank
    private String name;

    /**
     * The author of the manga
     */
    @NotBlank
    private String author;

    /**
     * IF you have a comment
     */
    @NotBlank
    private String comment;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(final String author) {
        this.author = author;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(final String comment) {
        this.comment = comment;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public BOOK_TYPE getType() {
        return type;
    }

    public void setType(final BOOK_TYPE type) {
        this.type = type;
    }

    /**
     * The url where you can scrap the website
     */
    @NotBlank
    private String url;

    /**
     * BookType
     */
    @NotBlank
    private BOOK_TYPE type;
}
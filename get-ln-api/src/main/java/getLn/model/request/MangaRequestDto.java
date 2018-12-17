package getLn.model.request;

import java.io.Serializable;
import java.util.StringJoiner;

import org.hibernate.validator.constraints.NotBlank;

import getln.data.entity.BOOK_TYPE;

@lombok.Data
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

    @Override
    public String toString() {
        return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]").add("name = " + name).add("author = " + author)
            .add("comment = " + comment).add("url = " + url).add("type = " + type).toString();
    }
}

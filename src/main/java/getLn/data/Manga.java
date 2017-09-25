package getLn.data;

import javax.persistence.Entity;

/**
 * .
 */
@Entity
public class Manga extends AbstractJpaEntity<Long>{

    private String name;
    private String author;
    private String Comment;
    private String url;
    private BOOK_TYPE type;
}

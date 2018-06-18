package getln.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import getln.data.commons.AbstractDeletableJpaEntity;

/**
 * .
 */
@Entity
@Table(name = "manga_subscription", schema = "LN")
@lombok.Data
public class MangaSubscription extends AbstractDeletableJpaEntity<Long> {

    /**
     * The user who subscript
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * The manga
     */
    @ManyToOne
    @JoinColumn(name = "manga_id")
    private Manga manga;

    /**
     * The number of the chapter since you subscribe
     */
    @Column(name = "num_chapter")
    private int numChapter;

    /**
     * The type of book you want receive epub or mobi
     */
    @Enumerated(EnumType.STRING)
    private BOOK_FORMAT format;

    public MangaSubscription() {
    }

}

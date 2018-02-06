package getln.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import getln.data.commons.AbstractDeletableJpaEntity;

/**
 * .
 */
@Entity
@Table(name = "manga_subscription", schema = "LN")
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

    public MangaSubscription() {
    }

    /**
     * Sets new The number of the chapter since you subscribe.
     *
     * @param numChapter New value of The number of the chapter since you subscribe.
     */
    public void setNumChapter(final int numChapter) {
        this.numChapter = numChapter;
    }

    /**
     * Gets The number of the chapter since you subscribe.
     *
     * @return Value of The number of the chapter since you subscribe.
     */
    public int getNumChapter() {
        return numChapter;
    }

    /**
     * Gets The manga.
     *
     * @return Value of The manga.
     */
    public Manga getManga() {
        return manga;
    }

    /**
     * Gets The user who subscript.
     *
     * @return Value of The user who subscript.
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets new The user who subscript.
     *
     * @param user New value of The user who subscript.
     */
    public void setUser(final User user) {
        this.user = user;
    }

    /**
     * Sets new The manga.
     *
     * @param manga New value of The manga.
     */
    public void setManga(final Manga manga) {
        this.manga = manga;
    }
}

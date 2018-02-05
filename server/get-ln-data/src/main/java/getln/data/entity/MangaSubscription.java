package getln.data.entity;

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

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public Manga getManga() {
        return manga;
    }

    public void setManga(final Manga manga) {
        this.manga = manga;
    }

    public MangaSubscription() {
    }

}
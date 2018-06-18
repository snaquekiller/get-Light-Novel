package getln.data.entity;

import java.util.Date;
import java.util.StringJoiner;

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
@Table(name = "manga_out", schema = "LN")
@lombok.Data
public class MangaOut extends AbstractDeletableJpaEntity<Long> {

    /**
     * hours
     **/
    @Column
    private int hours;

    /**
     * minutes
     **/
    @Column
    private int minutes;

    /**
     * secondes
     **/
    @Column
    private int secondes;

    /**
     * the days
     */
    @Column
    private Integer days;

    /**
     * the days
     */
    @Column(name = "nb_try")
    private int nbTry;

    /**
     * the status of the mangaout if we already try or not
     */
    @Enumerated(EnumType.STRING)
    private Status status;

    /**
     * The manga
     */
    @ManyToOne
    @JoinColumn(name = "manga_id")
    private Manga manga;

    public MangaOut() {
    }

    public MangaOut(final int hours, final int minutes, final int secondes, final Integer days) {
        this.hours = hours;
        this.minutes = minutes;
        this.secondes = secondes;
        this.days = days;
    }

    /**
     * Sets new The manga.
     *
     * @param manga New value of The manga.
     */
    public void setManga(final Manga manga) {
        update();
        this.manga = manga;
    }

    /**
     * Sets new the status of the mangaout if we already try or not.
     *
     * @param status New value of the status of the mangaout if we already try or not.
     */
    public void setStatus(final Status status) {
        update();
        this.status = status;
    }

    public void update() {
        updateDate = new Date();
    }

    /**
     * Sets new the days.
     *
     * @param nbTry New value of the days.
     */
    public void setNbTry(final int nbTry) {
        update();
        this.nbTry = nbTry;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]").add("hours = " + hours)
            .add("minutes = " + minutes).add("secondes = " + secondes).add("days = " + days).add("nbTry = " + nbTry)
            .add("status = " + status).add("manga = " + manga).add("creationDate = " + creationDate)
            .add("updateDate = " + updateDate).add("deleted = " + deleted).add("id = " + id).toString();
    }
}

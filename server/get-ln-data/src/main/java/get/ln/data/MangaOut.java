package get.ln.data;

import java.util.StringJoiner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import get.ln.data.commons.AbstractDeletableJpaEntity;

/**
 * .
 */
@Entity
@Table(name = "manga_out", schema = "LN")
public class MangaOut extends AbstractDeletableJpaEntity<Long> {

    /** hours **/
    @Column
    private int hours;

    /** minutes **/
    @Column
    private int minutes;

    /** secondes **/
    @Column
    private int secondes;

    /** the days */
    @Column
    private Integer days;

    /** the days */
    @Column(name = "nb_try")
    private int nbTry;

    /** the status of the mangaout if we already try or not */
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
     * Gets hours.
     *
     * @return Value of hours.
     */
    public int getHours() {
        return hours;
    }

    /**
     * Sets new minutes.
     *
     * @param minutes New value of minutes.
     */
    public void setMinutes(final int minutes) {
        this.minutes = minutes;
    }

    /**
     * Sets new secondes.
     *
     * @param secondes New value of secondes.
     */
    public void setSecondes(final int secondes) {
        this.secondes = secondes;
    }

    /**
     * Gets the days.
     *
     * @return Value of the days.
     */
    public Integer getDays() {
        return days;
    }

    /**
     * Sets new the days.
     *
     * @param days New value of the days.
     */
    public void setDays(final Integer days) {
        this.days = days;
    }

    /**
     * Gets secondes.
     *
     * @return Value of secondes.
     */
    public int getSecondes() {
        return secondes;
    }

    /**
     * Gets minutes.
     *
     * @return Value of minutes.
     */
    public int getMinutes() {
        return minutes;
    }

    /**
     * Sets new hours.
     *
     * @param hours New value of hours.
     */
    public void setHours(final int hours) {
        this.hours = hours;
    }

    /**
     * Sets new The manga.
     *
     * @param manga New value of The manga.
     */
    public void setManga(final Manga manga) {
        this.manga = manga;
    }

    /**
     * Sets new the status of the mangaout if we already try or not.
     *
     * @param status New value of the status of the mangaout if we already try or not.
     */
    public void setStatus(final Status status) {
        this.status = status;
    }

    /**
     * Sets new the days.
     *
     * @param nbTry New value of the days.
     */
    public void setNbTry(final int nbTry) {
        this.nbTry = nbTry;
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
     * Gets the days.
     *
     * @return Value of the days.
     */
    public int getNbTry() {
        return nbTry;
    }

    /**
     * Gets the status of the mangaout if we already try or not.
     *
     * @return Value of the status of the mangaout if we already try or not.
     */
    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]").add("hours = " + hours)
            .add("minutes = " + minutes).add("secondes = " + secondes).add("days = " + days).add("nbTry = " + nbTry)
            .add("status = " + status).add("manga = " + manga).add("creationDate = " + creationDate)
            .add("updateDate = " + updateDate).add("deleted = " + deleted).add("id = " + id).toString();
    }
}

package get.ln.data;

import java.util.StringJoiner;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * .
 */
@Entity
@Table(name = "manga_out", schema = "LN")
public class MangaOut extends AbstractDeletableJpaEntity<Long> {

    /** hours **/
    private int hours;

    /** minutes **/
    private int minutes;

    /** secondes **/
    private int secondes;

    /** the days */
    private Integer days;

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

    @Override
    public String toString() {
        return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]").add("hours = " + hours)
            .add("minutes = " + minutes).add("secondes = " + secondes).add("days = " + days).add("creationDate = " + creationDate)
            .add("deletionDate = " + updateDate).add("deleted = " + deleted).add("id = " + id).toString();
    }
}

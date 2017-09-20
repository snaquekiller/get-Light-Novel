package getLn.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * JPA entity that can be deleted.
 *
 * @param <I> The id type.
 */
@MappedSuperclass
public abstract class AbstractDeletableJpaEntity<I extends Serializable> extends AbstractJpaEntity<I> {

    /** The serial version UID. */
    private static final long serialVersionUID = 1L;

    /** The creation date. */
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    /** The deletion date. */
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletionDate;

    /** Whether the entity has been deleted. */
    @Column
    private boolean deleted;

    /**
     * {@inheritDoc}
     */
    public Date getCreationDate() {
        if (creationDate == null) {
            return null;
        }
        return new Date(creationDate.getTime());
    }

    /**
     * Sets the creation date.
     *
     * @param date The creation date to set.
     */
    public void setCreationDate(final Date date) {
        if (date != null) {
            creationDate = new Date(date.getTime());
        }
    }

    /**
     * {@inheritDoc}
     */
    public Date getDeletionDate() {
        if (deletionDate == null) {
            return null;
        }
        return new Date(deletionDate.getTime());
    }

    /**
     * {@inheritDoc}
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * {@inheritDoc}
     */
    public void delete() {
        deleted = true;
        deletionDate = new Date();
    }

    /**
     * {@inheritDoc}
     */
    public void restore() {
        deleted = false;
        deletionDate = null;
    }

}

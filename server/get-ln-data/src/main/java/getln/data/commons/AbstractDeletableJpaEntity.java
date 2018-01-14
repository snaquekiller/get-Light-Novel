package getln.data.commons;

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

    /** The creation date. */
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date creationDate;

    /** The deletion date. */
    @Column(name = "update_date")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date updateDate;

    /** Whether the entity has been deleted. */
    @Column
    protected boolean deleted;

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
    public Date getUpdateDate() {
        if (updateDate == null) {
            return null;
        }
        return new Date(updateDate.getTime());
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
        updateDate = new Date();
    }

    /**
     * {@inheritDoc}
     */
    public void restore() {
        deleted = false;
        updateDate = null;
    }

}

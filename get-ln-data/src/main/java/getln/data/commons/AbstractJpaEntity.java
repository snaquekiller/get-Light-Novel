package getln.data.commons;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * JPA entity.
 *
 * @param <I> The id type.
 */
@MappedSuperclass
public abstract class AbstractJpaEntity<I extends Serializable> {

    /** The serial version UID. */
    private static final long serialVersionUID = 1L;

    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected I id;

    /**
     * {@inheritDoc}
     */
    public I getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id The id to set.
     */
    public void setId(final I id) {
        this.id = id;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final AbstractJpaEntity that = (AbstractJpaEntity) o;

        return Objects.equals(this.id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

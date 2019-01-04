package getln.data.entity;

import java.util.StringJoiner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import getln.data.commons.AbstractDeletableJpaEntity;

/**
 * .
 */
@Entity
@Table(name = "file", schema = "LN")
@lombok.Data
public class FileStorage extends AbstractDeletableJpaEntity<Long> {

    /**
     * the name of the file
     **/
    @Column
    private String name;

    /**
     * the type of the file
     **/
    @Column
    private String type;

    /**
     * the url
     **/
    @Column
    private String url;

    public FileStorage() {

    }

    public FileStorage(final String name, final String type, final String url) {
        this.name = name;
        this.type = type;
        this.url = url;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]").add("name = " + name).add("type = " + type)
            .add("url = " + url).add("creationDate = " + creationDate).add("deletionDate = " + updateDate)
            .add("deleted = " + deleted).add("id = " + id).toString();
    }
}

package getLn.data;

import java.util.StringJoiner;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * .
 */
@Entity
@Table(name = "file", schema = "LN")
public class File extends AbstractDeletableJpaEntity<Long> {

    /** the name of the file **/
    private String name;

    /** the type of the file **/
    private String type;

    /** the url **/
    private String url;

    public File() {

    }

    public File(final String name, final String type, final String url) {
        this.name = name;
        this.type = type;
        this.url = url;
    }

    /**
     * Gets the type of the file.
     *
     * @return Value of the type of the file.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets new the url.
     *
     * @param url New value of the url.
     */
    public void setUrl(final String url) {
        this.url = url;
    }

    /**
     * Sets new the type of the file.
     *
     * @param type New value of the type of the file.
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * Sets new the name of the file.
     *
     * @param name New value of the name of the file.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets the name of the file.
     *
     * @return Value of the name of the file.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the url.
     *
     * @return Value of the url.
     */
    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]").add("name = " + name).add("type = " + type)
            .add("url = " + url).add("creationDate = " + creationDate).add("deletionDate = " + updateDate)
            .add("deleted = " + deleted).add("id = " + id).toString();
    }
}

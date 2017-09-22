package getLn.data;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * .
 */
@Entity
public class Chapter extends AbstractJpaEntity<Long> {

    @Column(name = "name")
    private String name;

}

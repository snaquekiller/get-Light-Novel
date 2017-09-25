package getLn.data;

import javax.persistence.Entity;

/**
 * .
 */
@Entity
public class ChapterOut extends AbstractDeletableJpaEntity<Long> {

    private int hours;
    private int minutes;
    private int secondes;
    private Integer days;


}

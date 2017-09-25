package getLn.data;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * .
 */
@Entity
public class Chapter extends AbstractDeletableJpaEntity<Long> {

    @Column(name = "name")
    private String name;

    private  String texte;

    private int num = 0;

    private String title;

    private String Tome;

    private File file;


}

package getLn.data;

import javax.persistence.Entity;

/**
 * .
 */
@Entity
public class User extends AbstractDeletableJpaEntity<Long> {

    private String email;

    private String nom;

    private String prenom;

    private String pseudo;
}

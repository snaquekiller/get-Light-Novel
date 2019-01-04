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
@Table(name = "user", schema = "LN")
@lombok.Data
public class User extends AbstractDeletableJpaEntity<Long> {

    /**
     * the email of the user
     **/
    @Column
    private String email;

    /**
     * The name of the user
     **/
    @Column
    private String nom;

    /**
     * Prenom
     **/
    @Column
    private String prenom;

    /**
     * the pseudo
     **/
    @Column
    private String pseudo;

    public User() {
    }

    public User(final String email, final String nom, final String prenom, final String pseudo) {
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.pseudo = pseudo;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]").add("email = " + email).add("nom = " + nom)
            .add("prenom = " + prenom).add("pseudo = " + pseudo).add("creationDate = " + creationDate)
            .add("deletionDate = " + updateDate).add("deleted = " + deleted).add("id = " + id).toString();
    }
}

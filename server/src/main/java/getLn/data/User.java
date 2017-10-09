package getLn.data;

import java.util.StringJoiner;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * .
 */
@Entity
@Table(name = "user", schema = "LN")
public class User extends AbstractDeletableJpaEntity<Long> {

    /** the email of the user **/
    private String email;

    /** The name of the user **/
    private String nom;

    /** Prenom **/
    private String prenom;

    /** the pseudo **/
    private String pseudo;

    public User() {
    }

    public User(final String email, final String nom, final String prenom, final String pseudo) {
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.pseudo = pseudo;
    }

    /**
     * Sets new the pseudo.
     *
     * @param pseudo New value of the pseudo.
     */
    public void setPseudo(final String pseudo) {
        this.pseudo = pseudo;
    }

    /**
     * Sets new the email of the user.
     *
     * @param email New value of the email of the user.
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * Gets The name of the user.
     *
     * @return Value of The name of the user.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Gets the email of the user.
     *
     * @return Value of the email of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets new The name of the user.
     *
     * @param nom New value of The name of the user.
     */
    public void setNom(final String nom) {
        this.nom = nom;
    }

    /**
     * Gets the pseudo.
     *
     * @return Value of the pseudo.
     */
    public String getPseudo() {
        return pseudo;
    }

    /**
     * Sets new Prenom.
     *
     * @param prenom New value of Prenom.
     */
    public void setPrenom(final String prenom) {
        this.prenom = prenom;
    }

    /**
     * Gets Prenom.
     *
     * @return Value of Prenom.
     */
    public String getPrenom() {
        return prenom;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]").add("email = " + email).add("nom = " + nom)
            .add("prenom = " + prenom).add("pseudo = " + pseudo).add("creationDate = " + creationDate)
            .add("deletionDate = " + updateDate).add("deleted = " + deleted).add("id = " + id).toString();
    }
}

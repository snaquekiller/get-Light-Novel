package getLn.model.request;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.io.Serializable;


public class UserRequestDto implements Serializable {

    @NotBlank
    @Size(max = 200)
    private String nom;

    @NotBlank
    @Size(max = 200)
    private String prenom;

    @NotBlank
    @Size(max = 200)
    @Email
    private String email;

    @NotBlank
    @Size(max = 200)
    private String pseudo;

    public String getNom() {
        return nom;
    }

    public void setNom(final String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(final String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(final String pseudo) {
        this.pseudo = pseudo;
    }
}

package getLn.model.request;

import java.io.Serializable;
import java.util.StringJoiner;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@lombok.Data
public class UserRequestDto implements Serializable {

    @NotBlank
    @Size(max = 200)
    private String nom;

    @NotBlank
    @Size(max = 200)
    private String prenom;

    /**
     * The email
     */
    @NotBlank
    @Size(max = 200)
    @Email
    private String email;

    /**
     * The pseudo
     */
    @NotBlank
    @Size(max = 200)
    private String pseudo;

    @Override
    public String toString() {
        return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]").add("nom = " + nom).add("prenom = " + prenom)
            .add("email = " + email).add("pseudo = " + pseudo).toString();
    }
}

package getLn.model.request;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@lombok.Data
public class UserRequestDto implements Serializable {

    @Size(max = 200)
    private String nom;

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

    /**
     * The pseudo
     */
    @NotBlank
    @Size(max = 50)
    private String password;

}

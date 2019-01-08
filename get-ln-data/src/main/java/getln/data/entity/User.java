package getln.data.entity;

import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import getln.data.commons.AbstractDeletableJpaEntity;
import lombok.ToString;

/**
 * .
 */
@Entity
@Table(name = "user", schema = "LN")
@lombok.Data
@ToString(exclude="password")
public class User extends AbstractDeletableJpaEntity<Long> implements UserDetails {

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

    @Column
    private String password;

    @Column
    private String role;


    public User() {
    }

    public User(final String email, final String nom, final String prenom, final String pseudo, final String password) {
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.pseudo = pseudo;
        this.password = password;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return  List.of(new SimpleGrantedAuthority("ROLE_" + role));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !isDeleted();
    }
}

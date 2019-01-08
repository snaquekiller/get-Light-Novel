package getln.service.common;

import java.util.Objects;
import java.util.Optional;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import getln.data.entity.QUser;
import getln.data.entity.User;
import getln.data.service.UserPersistenceService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserSqlService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Inject
    private UserPersistenceService userPersistenceService;


    /**
     * Create an user if the email is not already exist on the database
     *
     * @param email
     * @param name
     * @param firstname
     * @param pseudo
     * @return
     */
    public User createUser(final String email, final String name, final String firstname, final String pseudo,
            final String password) {
        Objects.requireNonNull(email);
        final long count = this.userPersistenceService.count(QUser.user.email.eq(email));
        if (count == 0L) {
            final User user = new User(email, name, firstname, pseudo, password);
            return this.userPersistenceService.save(user);
        }
        return null;
    }

    public Optional<User> findOne(Long id) {
        Objects.requireNonNull(id);
        return userPersistenceService.findById(id);
    }

    public User findByUsername(String username) throws UsernameNotFoundException {
        log.info("log with credential name: " + username);
        Objects.requireNonNull(username);
        return  userPersistenceService.findOne(QUser.user.email.eq(username)).orElseGet(null);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("log with credential name: " + username);
        Objects.requireNonNull(username);
        return findByUsername(username);
    }
}

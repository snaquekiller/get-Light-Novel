package getln.service.common;

import java.util.Objects;
import java.util.Optional;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import getln.data.entity.QUser;
import getln.data.entity.User;
import getln.data.service.UserPersistenceService;

@Service
public class UserSqlService {

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
    public User createUser(final String email, final String name, final String firstname, final String pseudo) {
        Objects.requireNonNull(email);
        final long count = this.userPersistenceService.count(QUser.user.email.eq(email));
        if (count == 0L) {
            final User user = new User(email, name, firstname, pseudo);
            return this.userPersistenceService.save(user);
        }
        return null;
    }

    public Optional<User> findOne(Long id) {
        Objects.requireNonNull(id);
        return userPersistenceService.findById(id);
    }
}

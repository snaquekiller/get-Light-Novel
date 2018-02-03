package getln.service.common;

import getln.data.entity.QUser;
import getln.data.entity.User;
import getln.data.service.UserPersistenceService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Objects;

@Service
public class UserService {

    @Inject
    private UserPersistenceService userPersistenceService;


    public User createUser(final String email, final String name, final String firstname, final String pseudo) {
        Objects.requireNonNull(email);
        final long count = userPersistenceService.count(QUser.user.email.eq(email));
        if (count == 0L) {
            final User user = new User(email, name, firstname, pseudo);
            return userPersistenceService.save(user);
        }
        return null;
    }
}

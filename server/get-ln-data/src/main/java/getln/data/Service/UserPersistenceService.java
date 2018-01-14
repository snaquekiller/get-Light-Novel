package getln.data.Service;

import org.springframework.stereotype.Service;

import getln.data.Entity.User;
import getln.data.commons.DataRepository;

/**
 * .
 */
@Service
public interface UserPersistenceService extends DataRepository<User> {

}

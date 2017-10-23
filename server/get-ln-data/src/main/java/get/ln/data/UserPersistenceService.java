package get.ln.data;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

/**
 * .
 */
@Service
public interface UserPersistenceService extends CrudRepository<User, Long>, QueryDslPredicateExecutor<User> {
    //public class ChapterPersistence implements Repository<Chapter, Long> {

}

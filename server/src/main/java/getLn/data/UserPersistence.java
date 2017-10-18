package getLn.data;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

/**
 * .
 */
@Service
public interface UserPersistence extends CrudRepository<User, Long> , QueryDslPredicateExecutor<User> {
    //public class ChapterPersistence implements Repository<Chapter, Long> {

}

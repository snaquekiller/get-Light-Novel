package getln.data.commons;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * .
 */
public interface DataRepository<I> extends CrudRepository<I, Long>, QueryDslPredicateExecutor<I> {

}

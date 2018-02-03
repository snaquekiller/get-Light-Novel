package getln.data.service;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * .
 */
@MappedSuperclass
public interface DataRepository<I> extends CrudRepository<I, Long>, QueryDslPredicateExecutor<I>, Serializable {

}

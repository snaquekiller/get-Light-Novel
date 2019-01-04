package getln.data.service;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * .
 */
@MappedSuperclass
@NoRepositoryBean
public interface DataRepository<I> extends CrudRepository<I, Long>, QuerydslPredicateExecutor<I>, Serializable {

}

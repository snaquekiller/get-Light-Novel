package get.ln.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

/**
 * .
 */
@Service
public interface ChapterPersistenceService extends CrudRepository<Chapter, Long> {

}

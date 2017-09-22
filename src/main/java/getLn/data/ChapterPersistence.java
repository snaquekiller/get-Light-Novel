package getLn.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

/**
 * .
 */
@Service
public interface ChapterPersistence extends CrudRepository<Chapter, Long> {

}

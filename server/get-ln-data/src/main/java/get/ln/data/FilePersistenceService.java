package get.ln.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

/**
 * .
 */
@Service
public interface FilePersistenceService extends CrudRepository<File, Long> {
    //public class ChapterPersistence implements Repository<Chapter, Long> {

}

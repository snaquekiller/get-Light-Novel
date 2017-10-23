package get.ln.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

/**
 * .
 */
@Service
public interface MangaOutPersistenceService extends CrudRepository<MangaOut, Long> {
    //public class ChapterPersistence implements Repository<Chapter, Long> {

}

package getLn.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

/**
 * .
 */
@Service
public interface MangaPersistence extends CrudRepository<Manga, Long> {
    //public class ChapterPersistence implements Repository<Chapter, Long> {

}
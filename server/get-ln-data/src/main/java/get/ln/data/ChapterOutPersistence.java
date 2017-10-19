package get.ln.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

/**
 * .
 */
@Service
public interface ChapterOutPersistence extends CrudRepository<ChapterOut, Long> {
    //public class ChapterPersistence implements Repository<Chapter, Long> {

}

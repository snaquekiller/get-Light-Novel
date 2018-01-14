package getln.data.Service;

import org.springframework.stereotype.Service;

import getln.data.Entity.Manga;
import getln.data.commons.DataRepository;

/**
 * .
 */
@Service
public interface MangaPersistenceService extends DataRepository<Manga> {

}

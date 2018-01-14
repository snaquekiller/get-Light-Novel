package getln.data.Service;

import org.springframework.stereotype.Service;

import getln.data.Entity.File;
import getln.data.commons.DataRepository;

/**
 * .
 */
@Service
public interface FilePersistenceService extends DataRepository<File> {

}

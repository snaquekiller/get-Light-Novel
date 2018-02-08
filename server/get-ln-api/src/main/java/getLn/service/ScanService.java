package getLn.service;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import getln.data.entity.MangaOut;
import getln.data.entity.Status;
import getln.data.service.MangaOutPersistenceService;
import getln.service.common.MangaOutService;

/**
 * .
 */
@Service
public class ScanService {

    /**
     * The logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ScanService.class);

    @Inject
    private MangaOutService mangaOutService;

    @Inject
    private MangaOutPersistenceService mangaOutPersistenceService;

    @Inject
    private EpubService epubService;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void setStatus(final MangaOut mangaOut, final Status status) {
        mangaOut.setStatus(status);
        mangaOutPersistenceService.save(mangaOut);
    }

    public void scanAndSendNewManga() {
        mangaOutService.getNextManga().forEach(mangaOut -> {
            try {
                LOGGER.info("Try to found a new manga for chapter={}", mangaOut);
                setStatus(mangaOut, Status.IN_PROGRESS);

                //TODO need to check if the chapter is not already found
                epubService.transformOneChapter(mangaOut.getManga());
                mangaOut.setNbTry(0);
                setStatus(mangaOut, Status.RE_TRY);
            } catch (final Exception e) {
                LOGGER.error("Can't found the manga={}", mangaOut, e);
                mangaOut.setNbTry(mangaOut.getNbTry() + 1);
                setStatus(mangaOut, Status.RE_TRY);
            }
        });
    }

}

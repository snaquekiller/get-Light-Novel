package getLn.service;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import getln.data.Entity.MangaOut;
import getln.data.Entity.Status;
import getln.data.Service.MangaOutPersistenceService;
import getln.service.common.MangaOutService;

/**
 * .
 */
@Service
public class ScanService {

    /** The logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ScanService.class);

    @Inject
    private MangaOutService mangaOutService;

    @Inject
    private MangaOutPersistenceService mangaOutPersistenceService;

    @Inject
    private EpubService epubService;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void setStatus(final MangaOut mangaOut) {
        mangaOut.setStatus(Status.IN_PROGRESS);
        mangaOutPersistenceService.save(mangaOut);
    }

    public void scanAndSendNewManga() {
        mangaOutService.getNextManga().forEach(mangaOut -> {
            setStatus(mangaOut);
            epubService.transformOneChapter(mangaOut.getManga());
        });
    }

}

package getln.service.common;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import getln.data.Entity.MangaSubscription;
import getln.data.Entity.QMangaSubscription;
import getln.data.Service.MangaSubscriptionPersistanceService;

/**
 * .
 */
@Service
public class MangaSubscriptionService {

    @Inject
    private MangaSubscriptionPersistanceService mangaSubscriptionPersistanceService;

    public Iterable<MangaSubscription> findByMangaId(final Long id) {
        return mangaSubscriptionPersistanceService.findAll(QMangaSubscription.mangaSubscription.manga.id.eq(id));
    }
}

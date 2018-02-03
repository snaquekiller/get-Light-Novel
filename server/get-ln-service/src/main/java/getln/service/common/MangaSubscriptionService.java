package getln.service.common;

import getln.data.entity.MangaSubscription;
import getln.data.entity.QMangaSubscription;
import getln.data.service.MangaSubscriptionPersistanceService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

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

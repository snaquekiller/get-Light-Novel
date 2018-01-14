package getln.data.Service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import getln.data.Entity.MangaSubscription;
import getln.data.QMangaSubscription;

/**
 * .
 */
@Service
public class MangaSubscriptionService {

    @Inject
    private MangaSubscriptionPersistanceService mangaSubscriptionPersistanceService;

    public Iterable<MangaSubscription> findByMangaId(Long id) {
        return mangaSubscriptionPersistanceService.findAll(QMangaSubscription.mangaSubscription.manga.id.eq(id));
    }
}

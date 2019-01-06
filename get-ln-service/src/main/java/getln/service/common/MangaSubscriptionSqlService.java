package getln.service.common;

import javax.inject.Inject;

import getln.data.entity.*;
import org.springframework.stereotype.Service;

import getln.data.service.MangaSubscriptionPersistanceService;

import java.util.List;

/**
 * .
 */
@Service
public class MangaSubscriptionSqlService {

    @Inject
    private MangaSubscriptionPersistanceService mangaSubscriptionPersistanceService;

    /**
     * Find the mangaSubscription by the manga Id
     *
     * @param id
     * @return
     */
    public Iterable<MangaSubscription> findByMangaId(final Long id) {
        return this.mangaSubscriptionPersistanceService.findAll(QMangaSubscription.mangaSubscription.manga.id.eq(id));
    }

    /**
     * Find the mangaSubscription by the manga Id
     *
     * @param id
     * @return
     */
    public Iterable<MangaSubscription> findByMangaIdAndUserId(final Long id, Long userId) {
        return this.mangaSubscriptionPersistanceService.findAll(QMangaSubscription.mangaSubscription.manga.id.eq(id).and(QMangaSubscription.mangaSubscription.user.id.eq(userId)));
    }

    public void delete(List<MangaSubscription> mangaSubscriptionList){
        mangaSubscriptionPersistanceService.deleteAll(mangaSubscriptionList);
    }
    public void add(BOOK_FORMAT book_format, Manga manga, User user, double chapterNum ){
        MangaSubscription mangaSubscription = new MangaSubscription();
        mangaSubscription.setManga(manga);
        mangaSubscription.setFormat(book_format);
        mangaSubscription.setUser(user);
        mangaSubscription.setNumChapter(chapterNum);

    }

}

package getln.service.common;

import java.util.List;

import javax.inject.Inject;

import com.querydsl.core.types.dsl.BooleanExpression;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import getln.data.entity.BOOK_FORMAT;
import getln.data.entity.Manga;
import getln.data.entity.MangaSubscription;
import getln.data.entity.QMangaSubscription;
import getln.data.entity.User;
import getln.data.service.MangaSubscriptionPersistanceService;

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
        return this.mangaSubscriptionPersistanceService.findAll(QMangaSubscription.mangaSubscription.manga.id.eq(id)
                .and(QMangaSubscription.mangaSubscription.user.id.eq(userId)));
    }

    public Page<MangaSubscription> listMangaSub(User user, int page, int limit, String sort, String order,
            String search) {
        PageRequest pageRequest = PageRequest.of(page, limit, Direction.fromString(order), sort);
        BooleanExpression expression = QMangaSubscription.mangaSubscription.user.id.eq(user.getId());
        if (search != null && !search.isEmpty()) {
            expression = expression.and(QMangaSubscription.mangaSubscription.manga.name.contains(search)
                    .or(QMangaSubscription.mangaSubscription.manga.comment.contains(search)));
        }
        Page<MangaSubscription> all = this.mangaSubscriptionPersistanceService.findAll(expression, pageRequest);
        return all;
    }


    public void delete(List<MangaSubscription> mangaSubscriptionList) {
        mangaSubscriptionPersistanceService.deleteAll(mangaSubscriptionList);
    }

    public MangaSubscription add(BOOK_FORMAT book_format, Manga manga, User user, double chapterNum) {
        MangaSubscription mangaSubscription = new MangaSubscription();
        mangaSubscription.setManga(manga);
        mangaSubscription.setFormat(book_format);
        mangaSubscription.setUser(user);
        mangaSubscription.setNumChapter(chapterNum);
        return mangaSubscriptionPersistanceService.save(mangaSubscription);
    }

}

package getln.service.common;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import com.google.common.collect.Lists;
import com.querydsl.core.types.dsl.BooleanExpression;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import getln.data.entity.BOOK_TYPE;
import getln.data.entity.Manga;
import getln.data.entity.QManga;
import getln.data.service.MangaPersistenceService;

@Service
public class MangaSqlService {

    @Inject
    private MangaPersistenceService mangaPersistenceService;

    /**
     * Add a new manga if the url and the name don't already exist
     *
     * @param name
     * @param author
     * @param comment
     * @param url
     * @param type
     * @return
     */
    public Manga addManga(final String name, final String author, final String comment, final String url,
            final BOOK_TYPE type) {
        final long count = this.mangaPersistenceService.count(QManga.manga.url.eq(url).or(QManga.manga.name.eq(name)));
        if (count == 0) {
            final Manga manga = new Manga(name, author, comment, url, type);
            return this.mangaPersistenceService.save(manga);
        }
        return null;
    }


    public Page<Manga> listManga(int page, int limit, String sort, String order, String search) {
        PageRequest pageRequest = PageRequest.of(page, limit, Direction.fromString(order), sort);
        BooleanExpression expression = QManga.manga.deleted.isFalse();
        if(search != null && !search.isEmpty()) {
            expression = expression.and(QManga.manga.name.contains(search).or(QManga.manga.comment.contains(search)));
        }
        Page<Manga> all = this.mangaPersistenceService.findAll(expression, pageRequest);
        return all;
    }

    /**
     * Find a manga by his id
     *
     * @param id
     * @return
     */
    public Optional<Manga> findById(Long id) {
        return this.mangaPersistenceService.findOne(QManga.manga.id.eq(id));
    }

    /**
     * Find a manga by his url
     *
     * @param url
     * @return
     */
    public List<Manga> findByUrl(String url) {
        return Lists.newArrayList(this.mangaPersistenceService.findAll(QManga.manga.url.contains(url)));
    }
}

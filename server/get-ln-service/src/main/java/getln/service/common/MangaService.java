package getln.service.common;

import com.google.common.collect.Lists;
import getln.data.entity.BOOK_TYPE;
import getln.data.entity.Manga;
import getln.data.entity.QManga;
import getln.data.service.MangaPersistenceService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class MangaService {

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
    public Manga addManga(final String name, final String author, final String comment, final String url, final BOOK_TYPE type) {
        final long count = this.mangaPersistenceService.count(QManga.manga.url.eq(url).or(QManga.manga.name.eq(name)));
        if (count == 0) {
            final Manga manga = new Manga(name, author, comment, url, type);
            return this.mangaPersistenceService.save(manga);
        }
        return null;
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

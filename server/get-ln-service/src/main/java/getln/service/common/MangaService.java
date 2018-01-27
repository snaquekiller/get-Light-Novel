package getln.service.common;

import getln.data.Entity.BOOK_TYPE;
import getln.data.Entity.Manga;
import getln.data.Entity.QManga;
import getln.data.Service.MangaPersistenceService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class MangaService {

    @Inject
    private MangaPersistenceService mangaPersistenceService;


    public Manga addManga(final String name, final String author, final String comment, final String url, final BOOK_TYPE type) {

        final long count = mangaPersistenceService.count(QManga.manga.url.eq(url).or(QManga.manga.name.eq(name)));
        if (count == 0) {
            final Manga manga = new Manga(name, author, comment, url, type);
            return mangaPersistenceService.save(manga);
        }
        return null;
    }
}

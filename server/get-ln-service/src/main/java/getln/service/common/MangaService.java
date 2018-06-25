package getln.service.common;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import getln.data.commons.Iterables;
import getln.data.entity.BOOK_TYPE;
import getln.data.entity.Manga;
import getln.data.entity.QManga;
import getln.data.service.MangaPersistenceService;

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

    public List<Manga> findByUrl(String url) {
        return Iterables.toStream(mangaPersistenceService.findAll(QManga.manga.url.contains(url))).collect(Collectors.toList());
    }
}

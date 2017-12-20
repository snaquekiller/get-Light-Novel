package getLn.Service;

import javax.inject.Inject;

import com.querydsl.core.types.dsl.BooleanExpression;

import org.joda.time.DateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QSort;
import org.springframework.stereotype.Service;

import get.ln.data.Chapter;
import get.ln.data.ChapterPersistenceService;
import get.ln.data.Manga;
import get.ln.data.MangaPersistenceService;
import get.ln.data.QChapter;
import get.ln.data.QMangaOut;

/**
 * .
 */
@Service
public class ScanService {

    @Inject
    private MangaPersistenceService mangaPersistenceService;

    @Inject
    private ChapterPersistenceService chapterPersistenceService;

    private Chapter getLastChapter(Manga manga) {
        if(manga != null && manga.getId() != null) {
            Pageable page = new PageRequest(0, 1, new QSort(QChapter.chapter.creationDate.asc()));
            final Page<Chapter> all = chapterPersistenceService.findAll(QChapter.chapter.manga.id.eq(manga.getId()), page);
            return all.getContent().get(0);
        }
        return null;
    }

    private void getNextManga() {
        final DateTime dateTime = DateTime.now();
        final int dayOfYear = dateTime.getDayOfYear();
        final int hours = dateTime.getHourOfDay();
        final int minuteOfDay = dateTime.getMinuteOfDay();
        final QMangaOut mangaOut = QMangaOut.mangaOut;
        final BooleanExpression between = ((QMangaOut) mangaOut).hours.between(hours, hours + 1);

        mangaPersistenceService.findAll(QMangaOut.mangaOut.hours.eq(10));
    }
}

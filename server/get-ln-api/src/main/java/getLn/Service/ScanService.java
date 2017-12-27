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
import get.ln.data.MangaOut;
import get.ln.data.MangaOutPersistenceService;
import get.ln.data.MangaPersistenceService;
import get.ln.data.QChapter;
import get.ln.data.QMangaOut;
import get.ln.data.Status;

/**
 * .
 */
@Service
public class ScanService {

    @Inject
    private MangaPersistenceService mangaPersistenceService;

    @Inject
    private MangaOutPersistenceService mangaOutPersistenceService;

    @Inject
    private ChapterPersistenceService chapterPersistenceService;

    private Chapter getLastChapter(final Manga manga) {
        if (manga != null && manga.getId() != null) {
            final Pageable page = new PageRequest(0, 1, new QSort(QChapter.chapter.creationDate.asc()));
            final Page<Chapter> all = chapterPersistenceService.findAll(QChapter.chapter.manga.id.eq(manga.getId()), page);
            return all.getContent().get(0);
        }
        return null;
    }

    private void dd() {
        getNextManga().forEach(mangaOut -> {
            mangaOut.setStatus(Status.IN_PROGRESS);
            
        });

    }

    private Iterable<MangaOut> getNextManga() {
        final DateTime dateTime = DateTime.now();
        final int minuteOfHour = dateTime.getMinuteOfHour();
        final QMangaOut mangaOut = QMangaOut.mangaOut;
        final int less15 = minuteOfHour - 15;

        final BooleanExpression minutes;
        if (minuteOfHour < 15) {
            minutes = mangaOut.minutes.between(60 + less15, 60).or(mangaOut.minutes.between(0, minuteOfHour));
        } else {
            minutes = mangaOut.minutes.between(less15, minuteOfHour);
        }

        final int hours = dateTime.getHourOfDay();
        final BooleanExpression between = mangaOut.hours.between(hours, hours + 1)
            //minutes
            .and(minutes)
            //days
            .and(mangaOut.days.isNull().or(mangaOut.days.eq(dateTime.getDayOfWeek())))
            //
            .and(mangaOut.updateDate.between(dateTime.minusMinutes(15).toDate(), dateTime.toDate()));

        return mangaOutPersistenceService.findAll(between);
    }
}

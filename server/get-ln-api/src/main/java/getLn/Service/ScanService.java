package getLn.Service;

import javax.inject.Inject;

import com.querydsl.core.types.dsl.BooleanExpression;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import get.ln.data.MangaPersistenceService;
import get.ln.data.QMangaOut;

/**
 * .
 */
@Service
public class ScanService {

    @Inject
    private MangaPersistenceService mangaPersistenceService;

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

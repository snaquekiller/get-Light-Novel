package getLn.Service;

import javax.inject.Inject;

import com.querydsl.core.types.dsl.BooleanExpression;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import get.ln.data.MangaOut;
import get.ln.data.MangaOutPersistenceService;
import get.ln.data.MangaPersistenceService;
import get.ln.data.QMangaOut;
import get.ln.data.Status;

/**
 * .
 */
@Service
public class ScanService {

    /** The logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ScanService.class);

    @Inject
    private MangaPersistenceService mangaPersistenceService;

    @Inject
    private MangaOutPersistenceService mangaOutPersistenceService;





    @Transactional(propagation = Propagation.REQUIRES_NEW )
    public void setStatus(MangaOut mangaOut){
        mangaOut.setStatus(Status.IN_PROGRESS);
        mangaOutPersistenceService.save(mangaOut);
    }
    private void dd() {
        getNextManga().forEach(mangaOut -> {
            setStatus(mangaOut);

        });

    }

    private Iterable<MangaOut> getNextManga() {
        final DateTime dateTime = DateTime.now();
        final int minuteOfHour = dateTime.getMinuteOfHour();
        final QMangaOut mangaOut = QMangaOut.mangaOut;
        final int less15 = minuteOfHour - 15;
        final int hours = dateTime.getHourOfDay();

        final BooleanExpression minutesAndHours;
        if (minuteOfHour < 15) {
            minutesAndHours = mangaOut.minutes.between(60 + less15, 60).or(mangaOut.minutes.between(0, minuteOfHour))
                //hours
                .and(mangaOut.hours.between(hours, hours + 1));
        } else {
            minutesAndHours = mangaOut.minutes.between(less15, minuteOfHour)
                //hours
                .and(mangaOut.hours.eq(hours));
        }

        final BooleanExpression between = mangaOut.hours.between(hours, hours + 1)
            //minutesAndHours
            .and(minutesAndHours)
            //days
            .and(mangaOut.days.isNull().or(mangaOut.days.eq(dateTime.getDayOfWeek())))
            //
            .and(mangaOut.updateDate.between(dateTime.minusMinutes(15).toDate(), dateTime.toDate()))
            //status
            .and(mangaOut.status.eq(Status.AVAILABLE));

        return mangaOutPersistenceService.findAll(between);
    }
}

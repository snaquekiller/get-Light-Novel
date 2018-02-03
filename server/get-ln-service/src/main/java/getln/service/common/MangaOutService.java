package getln.service.common;

import com.querydsl.core.types.dsl.BooleanExpression;
import getln.data.entity.MangaOut;
import getln.data.entity.QMangaOut;
import getln.data.entity.Status;
import getln.data.service.MangaOutPersistenceService;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * .
 */
@Service
public class MangaOutService {

    @Inject
    private MangaOutPersistenceService mangaOutPersistenceService;

    public Iterable<MangaOut> getNextManga() {
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

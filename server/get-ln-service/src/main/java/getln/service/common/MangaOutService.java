package getln.service.common;

import javax.inject.Inject;

import com.querydsl.core.types.dsl.BooleanExpression;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import getln.data.entity.MangaOut;
import getln.data.entity.QMangaOut;
import getln.data.entity.Status;
import getln.data.service.MangaOutPersistenceService;

/**
 * .
 */
@Service
public class MangaOutService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MangaOutService.class);

    public static int MAX_TRY = 5;

    @Inject
    private MangaOutPersistenceService mangaOutPersistenceService;

    public Iterable<MangaOut> getNextManga() {
        LOGGER.info("Begin to get all Manga");
        final DateTime dateTime = DateTime.now();
        final int minuteOfHour = dateTime.getMinuteOfHour();
        final QMangaOut mangaOut = QMangaOut.mangaOut;
        final int less15 = minuteOfHour - 15;
        final int hours = dateTime.getHourOfDay();

        final BooleanExpression minutesAndHours;
        if (minuteOfHour < 15) {
            minutesAndHours = mangaOut.minutes.between(60 + less15, 60).or(mangaOut.minutes.between(0, minuteOfHour + 2))
                //hours
                .and(mangaOut.hours.between(hours - 1, hours));
        } else {
            minutesAndHours = mangaOut.minutes.between(less15, minuteOfHour + 2)
                //hours
                .and(mangaOut.hours.eq(hours));
        }

        final BooleanExpression expression = minutesAndHours
            //days
            .and(mangaOut.days.isNull().or(mangaOut.days.eq(dateTime.getDayOfWeek())))
            //
            .and(mangaOut.updateDate.between(dateTime.minusMinutes(15).toDate(), dateTime.toDate()))
            //status
            .and(mangaOut.status.eq(Status.AVAILABLE).or(mangaOut.status.eq(Status.RE_TRY).and(mangaOut.nbTry.loe(MAX_TRY))));

        LOGGER.info("Search manga out with predicate={}", expression);
        return mangaOutPersistenceService.findAll(expression);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package getLn;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import get.ln.data.MangaPersistenceService;
import get.ln.data.User;
import get.ln.data.UserPersistenceService;

/**
 * @author Nicolas
 */
@Service
public class ScheduledGetLn {

    private static final Logger log = LoggerFactory.getLogger(ScheduledGetLn.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Inject
    private Environment environment;

    @Inject
    private UserPersistenceService userPersistence;

    @Scheduled(cron = "${scheduled.getln.task}")
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
        log.info("The time is dd {}", environment.getProperty("ds.password"));
        final Iterable<User> all = userPersistence.findAll(QUser.us);
        log.info("The time is dd {}", all);
    }

    @Inject
    private MangaPersistenceService mangaPersistenceService;

    private void getNextManga() {
        mangaPersistenceService.findAll();
    }
}

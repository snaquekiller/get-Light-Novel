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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import getLn.data.User;
import getLn.data.UserPersistence;

/**
 * @author Nicolas
 */
@Service
public class ScheduledGetLn {

    private static final Logger log = LoggerFactory.getLogger(ScheduledGetLn.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private Environment environment;

    @Inject
    private UserPersistence userPersistence;

    @Scheduled(cron = "${scheduled.getln.task}")
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
        log.info("The time is dd {}", environment.getProperty("ds.password"));
        final Iterable<User> all = userPersistence.findAll();
        log.info("The time is dd {}", all);
    }
}

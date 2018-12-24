/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package getLn;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import getLn.service.ebook.MobiService;
import getLn.service.scrap.ScanService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Nicolas
 */
@Slf4j
@Service
public class ScheduledGetLn {


    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Inject
    private ScanService scanService;

    @Inject
    private MobiService mobiService;

    @Scheduled(cron = "${scheduled.getln.task}")
    public void newMangaCron() {
        //        log.info("The time is now {}", dateFormat.format(new Date()));
        this.scanService.scanAndSendNewManga();
    }

    //    @Scheduled(cron = "${scheduled.getln.task}")
    public void testEpub() {
        log.info("The time is now {}", dateFormat.format(new Date()));
        this.mobiService
                .epubToMbi(new File(
                        "testEpub/_Douluo_Dalu_3___Dragon_King_s_Legend_Douluo_Dalu_3___Dragon_King_s_Legend_1590.epub"));
    }

}

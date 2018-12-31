/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package getLn;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import javax.inject.Inject;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import getLn.service.EbookService;
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
    private EbookService ebookService;

    @Scheduled(cron = "${cron.task.getln}")
    public void newMangaln() {
//                log.info("The time is now {}", dateFormat.format(new Date()));
//        this.scanService.scanAndSendNewManga();
    }

    @Scheduled(cron = "${cron.task.newsMangaDoom}")
    public void newMangaCron() throws IOException {
        //        log.info("The time is now {}", dateFormat.format(new Date()));
        this.ebookService.newsFromMngDoom(Arrays.asList("nic.guitton@gmail.com", "nicolas.guitton@kindle.com"));
    }



}

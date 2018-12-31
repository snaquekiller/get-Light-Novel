package getLn.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import getLn.model.ChapterDto;
import getLn.service.EbookService;
import getLn.service.MailService;
import getLn.service.scrap.ScrapMngDoomService;
import getln.data.entity.BOOK_TYPE;
import getln.data.entity.Manga;
import getln.service.common.MangaSqlService;
import lombok.extern.slf4j.Slf4j;

/**
 * .
 */
@Slf4j
@RestController
public class TestController {
    @Inject
    private MailService mailService;

    @Inject
    private ScrapMngDoomService scrapMngDoomService;

    @Inject
    private EbookService ebookService;


    @Inject
    private MangaSqlService mangaService;


    @RequestMapping(value = "/test/manga", method = RequestMethod.GET)
    public void testManga(
            @RequestParam(required = false, defaultValue = "0") final Long id) throws Exception {
        Optional<Manga> byId = mangaService.findById(id);
        if (byId.isPresent()) {
            ebookService.transformOneChapter(byId.get(), Collections.emptyList());
        } else {
            Manga dd =
                    mangaService
                            .addManga("solo-leveling", "Jang Sung-Lak", "dd", "http://www.mngdoom.com/solo-leveling",
                                    BOOK_TYPE.MANGA);
            ebookService.transformOneChapter(dd, Arrays.asList("nic.guitton@gmail.com", "nicolas.guitton@kindle.com"));
        }
    }
    @RequestMapping(value = "/test/mangaDoom", method = RequestMethod.GET)
    public List<ChapterDto> testMangaDoom(
            @RequestParam(required = false, defaultValue = "0") final Long id) throws Exception {
       return scrapMngDoomService.scanLastScanOutPage();
    }

    @RequestMapping(value = "/test/newMngDoom", method = RequestMethod.GET)
    public void testUpdateMangaDoom(
            @RequestParam(required = false, defaultValue = "0") final Long id) throws Exception {
        ebookService.newsFromMngDoom(Arrays.asList("nic.guitton@gmail.com", "nicolas.guitton@kindle.com"));
    }


    @RequestMapping(value = "/test/mail", method = RequestMethod.GET)
    public void mail(
            @RequestParam(required = false, defaultValue = "0") final Long id) throws Exception {
        mailService
                .sendMail("nic.guitton@gmail.com", "data/solo_leveling/18.0/solo_leveling_18.mobi",
                        "solo_leveling_18.mobi");
    }

}

package getLn.controller;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import getLn.configuration.IsAdmin;
import getLn.model.ChapterDto;
import getLn.service.EbookService;
import getLn.service.ImageService;
import getLn.service.MailService;
import getLn.service.ebook.ZipService;
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
@IsAdmin
public class TestController {

    @Inject
    private MailService mailService;

    @Inject
    private ScrapMngDoomService scrapMngDoomService;

    @Inject
    private EbookService ebookService;


    @Inject
    private MangaSqlService mangaService;

    @Inject
    private ImageService imageService;

    @Inject
    private ZipService zipService;

    List<String> email = Arrays.asList("nic.guitton@gmail.com");//, "nicolas.guitton@kindle.com");


    @RequestMapping(value = "/test/manga", method = RequestMethod.GET)
    public void testManga(
            @RequestParam(required = false, defaultValue = "0") final Long id) throws Exception {
        Manga byId = mangaService.findById(id).orElse(mangaService
                .addManga("solo-leveling", "Jang Sung-Lak", "dd", "http://www.mngdoom.com/solo-leveling",
                        BOOK_TYPE.MANGA));
        ebookService.transformOneChapter(byId, email);
    }

    @RequestMapping(value = "/test/chapter", method = RequestMethod.GET)
    public void testChapter(
            @RequestParam(required = false, defaultValue = "0") final double number,
            @RequestParam(required = false, defaultValue = "0") final long mangaId) throws Exception {
        Manga byId = mangaService.findById(mangaId).orElse(mangaService
                .addManga("solo-leveling", "Jang Sung-Lak", "dd", "http://www.mngdoom.com/solo-leveling",
                        BOOK_TYPE.MANGA));

        // we scrap one
        final ChapterDto chapterXhtml = ebookService.scrapOne(byId, number);
        if (chapterXhtml == null) {
            throw new Exception("Can't scrap or not Found");
        }
        ebookService.transformAndSend(email, chapterXhtml);
    }

    @RequestMapping(value = "/test/mangaDoom", method = RequestMethod.GET)
    public List<ChapterDto> testMangaDoom(
            @RequestParam(required = false, defaultValue = "0") final Long id) throws Exception {
        return scrapMngDoomService.scanLastScanOutPage();
    }

    @RequestMapping(value = "/test/newMngDoom", method = RequestMethod.GET)
    public void testUpdateMangaDoom(
            @RequestParam(required = false, defaultValue = "0") final Long id) throws Exception {
        ebookService.newsFromMngDoom(email);
    }

    @RequestMapping(value = "/test/zip", method = RequestMethod.GET)
    public void zip(
            @RequestParam(required = false, defaultValue = "0") final Long id) throws Exception {
        File file = new File("data/solo_leveling/53.0/META-INF/container.xml");
        zipService.zipFile("solo_leveling/53.0","solo_leveling_53.zip",
                Collections.singletonList(file));
    }


    @RequestMapping(value = "/test/mail", method = RequestMethod.GET)
    public void mail(
            @RequestParam(required = false, defaultValue = "0") final Long id) throws Exception {
        mailService
                .sendMail("nic.guitton@gmail.com", "data/solo_leveling/18.0/solo_leveling_18.mobi",
                        "solo_leveling_18.mobi");
    }

    @RequestMapping(value = "/test/split", method = RequestMethod.GET)
    public void mail(
            @RequestParam(required = false, defaultValue = "0") final String filePath) throws Exception {
        log.info("split image ={}", filePath);
        imageService.splitImage(new File(filePath));
    }

}

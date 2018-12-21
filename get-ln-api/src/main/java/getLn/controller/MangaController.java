package getLn.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import javax.inject.Inject;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import getLn.model.request.MangaRequestDto;
import getLn.model.request.UserRequestDto;
import getLn.service.EbookService;
import getln.data.entity.BOOK_TYPE;
import getln.data.entity.Manga;
import getln.data.entity.User;
import getln.service.common.MangaSqlService;
import getln.service.common.UserSqlService;

/**
 * .
 */
@RestController
public class MangaController {

    @Inject
    private UserSqlService userService;

    @Inject
    private MangaSqlService mangaService;

    @Inject
    private EbookService ebookService;

    /**
     * List reviewers.
     *
     * @return the reviewers response
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public void addUser(@Validated @RequestBody(required = true) final UserRequestDto userRequestDto) {
        // @formatter:on
        final User user = userService
                .createUser(userRequestDto.getEmail(), userRequestDto.getNom(), userRequestDto.getPrenom(),
                        userRequestDto.getPseudo());
    }

    @RequestMapping(value = "/manga", method = RequestMethod.POST)
    public void addManga(@Validated @RequestBody(required = true) final MangaRequestDto mangaRequestDto) {
        mangaService.addManga(mangaRequestDto.getName(), mangaRequestDto.getAuthor(), mangaRequestDto.getComment(),
                mangaRequestDto.getUrl(), mangaRequestDto.getType());
    }

    @RequestMapping(value = "/subscribe/{mangaId}", method = RequestMethod.POST)
    public void subScribe(@Validated @RequestBody(required = true) final MangaRequestDto mangaRequestDto) {
        mangaService.addManga(mangaRequestDto.getName(), mangaRequestDto.getAuthor(), mangaRequestDto.getComment(),
                mangaRequestDto.getUrl(), mangaRequestDto.getType());
    }

    //    @RequestMapping(value = "/subscribe", method = RequestMethod.POST)
    //    public void addSubscribe(@Validated @RequestBody(required = true) final MangaSubscriptionRequestDto mangaRequestDto) {
    //        mangaService.addManga(mangaRequestDto.getName(), mangaRequestDto.getAuthor(), mangaRequestDto.getComment(), mangaRequestDto.getUrl(), mangaRequestDto.getType());
    //    }

    @RequestMapping(value = "/test/manga", method = RequestMethod.GET)
    public void subScribe(
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


    /**
     * List reviewers.
     *
     * @return the reviewers response
     */
    @RequestMapping(value = "/chapter", method = RequestMethod.GET)
    public String listRevdsqdiewers() {
        // @formatter:on

        return "pascoucocu";
    }

}

package getLn.controller;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import getLn.model.MangaDto;
import getLn.model.request.MangaRequestDto;
import getLn.model.request.ScrapRequestDto;
import getLn.model.request.UserRequestDto;
import getLn.service.scrap.ScrapMngDoomService;
import getln.data.entity.User;
import getln.service.common.MangaSqlService;
import getln.service.common.UserSqlService;

/**
 * .
 */
@CrossOrigin
@RestController
public class MangaController {

    @Inject
    private UserSqlService userService;

    @Inject
    private MangaSqlService mangaService;

    @Inject
    private ScrapMngDoomService scrapMngDoomService;


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



    @RequestMapping(value = "/scrap", method = RequestMethod.POST)
    public MangaDto scrap(@Validated @RequestBody(required = true) final ScrapRequestDto scrapRequestDto)
            throws IOException {
        return scrapMngDoomService.scrapManga(scrapRequestDto.getUrl());
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

package getLn.controller;

import javax.inject.Inject;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import getLn.model.request.MangaRequestDto;
import getLn.model.request.UserRequestDto;
import getln.data.Entity.User;
import getln.service.common.MangaService;
import getln.service.common.UserService;

/**
 * .
 */
@RestController
public class MangaController {

    @Inject
    private UserService userService;

    @Inject
    private MangaService mangaService;

    /**
     * List reviewers.
     *
     * @return the reviewers response
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public void addUser(@Validated @RequestBody(required = true) final UserRequestDto userRequestDto) {
        // @formatter:on
        final User user = userService.createUser(userRequestDto.getEmail(), userRequestDto.getNom(), userRequestDto.getPrenom(),
            userRequestDto.getPseudo());
    }

    @RequestMapping(value = "/manga", method = RequestMethod.POST)
    public void addManga(@Validated @RequestBody(required = true) final MangaRequestDto mangaRequestDto) {
        mangaService.addManga(mangaRequestDto.getName(), mangaRequestDto.getAuthor(), mangaRequestDto.getComment(),
            mangaRequestDto.getUrl(), mangaRequestDto.getType());
    }

    //    @RequestMapping(value = "/subscribe", method = RequestMethod.POST)
    //    public void addSubscribe(@Validated @RequestBody(required = true) final MangaSubscriptionRequestDto mangaRequestDto) {
    //        mangaService.addManga(mangaRequestDto.getName(), mangaRequestDto.getAuthor(), mangaRequestDto.getComment(), mangaRequestDto.getUrl(), mangaRequestDto.getType());
    //    }

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

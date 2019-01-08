package getLn.controller;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import com.google.common.collect.Lists;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import getLn.model.MangaDto;
import getLn.model.request.MangaRequestDto;
import getLn.model.request.MangaSubscribeDto;
import getLn.model.request.ScrapRequestDto;
import getLn.model.request.UserRequestDto;
import getLn.service.scrap.ScrapMngDoomService;
import getln.data.entity.Manga;
import getln.data.entity.MangaSubscription;
import getln.data.entity.User;
import getln.service.common.MangaSqlService;
import getln.service.common.MangaSubscriptionSqlService;
import getln.service.common.UserSqlService;

/**
 * .
 */
@CrossOrigin
@RestController
public class MangaController {

    @Inject
    private UserSqlService userSqlService;

    @Inject
    private MangaSqlService mangaSqlService;

    @Inject
    private ScrapMngDoomService scrapMngDoomService;

    @Inject
    private MangaSubscriptionSqlService mangaSubscriptionSqlService;

    @Inject
    private PasswordEncoder passwordEncoder;


    /**
     * List reviewers.
     *
     * @return the reviewers response
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public User addUser(@Validated @RequestBody(required = true) final UserRequestDto userRequestDto) {
        // @formatter:on
        final User user = userSqlService
                .createUser(userRequestDto.getEmail(), userRequestDto.getNom(), userRequestDto.getPrenom(),
                        userRequestDto.getPseudo(), passwordEncoder.encode(userRequestDto.getPassword()));
        return user;
    }

    /**
     * List reviewers.
     *
     * @return the reviewers response
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public User login(@Validated @RequestBody(required = true) final UserRequestDto userRequestDto) {
        // @formatter:on
        final User user = userSqlService
                .createUser(userRequestDto.getEmail(), userRequestDto.getNom(), userRequestDto.getPrenom(),
                        userRequestDto.getPseudo(), passwordEncoder.encode(userRequestDto.getPassword()));
        return user;
    }

    @RequestMapping(value = "/manga", method = RequestMethod.POST)
    public void addManga(@Validated @RequestBody(required = true) final MangaRequestDto mangaRequestDto) {
        mangaSqlService.addManga(mangaRequestDto.getName(), mangaRequestDto.getAuthor(), mangaRequestDto.getComment(),
                mangaRequestDto.getUrl(), mangaRequestDto.getType());
    }

    @RequestMapping(value = "/subscribe/{mangaId}", method = RequestMethod.POST)
    public void subScribe(@Validated @RequestBody(required = true) final MangaSubscribeDto mangaSubscribeDto,
                          @PathVariable("mangaId") final Long mangaId) {
        Optional<Manga> byId = mangaSqlService.findById(mangaId);
        if(byId.isPresent()){
            Optional<User> one = userSqlService.findOne(mangaSubscribeDto.getUserId());
            if(one.isPresent()){
                List<MangaSubscription> byMangaIdAndUserId = Lists.newArrayList(mangaSubscriptionSqlService.findByMangaIdAndUserId(byId.get().getId(), one.get().getId()));
                    if(byMangaIdAndUserId.isEmpty()){
                        mangaSubscriptionSqlService.add(mangaSubscribeDto.getBookFormat(), byId.get(), one.get(), Double.parseDouble(mangaSubscribeDto.getChapterNum()));
                }else if(byMangaIdAndUserId.size() > 1){
                    Optional<MangaSubscription> min = byMangaIdAndUserId.stream().min(Comparator.comparingDouble(MangaSubscription::getNumChapter));
                    byMangaIdAndUserId.remove(min);
                    mangaSubscriptionSqlService.delete(byMangaIdAndUserId);
                }
            }
        }
    }

    //    @RequestMapping(value = "/subscribe", method = RequestMethod.POST)
    //    public void addSubscribe(@Validated @RequestBody(required = true) final MangaSubscriptionRequestDto mangaRequestDto) {
    //        mangaSqlService.addManga(mangaRequestDto.getName(), mangaRequestDto.getAuthor(), mangaRequestDto.getComment(), mangaRequestDto.getUrl(), mangaRequestDto.getType());
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

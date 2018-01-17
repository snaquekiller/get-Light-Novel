package getLn.service;

import java.io.File;
import java.util.Collections;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import getLn.model.ChapterDto;
import getln.data.Entity.Chapter;
import getln.data.Entity.Manga;
import getln.data.Entity.MangaSubscription;
import getln.data.Service.ChapterPersistenceService;
import getln.data.Service.FilePersistenceService;
import getln.service.ChapterService;
import getln.service.MangaSubscriptionService;

/**
 * .
 */
@Service
public class EpubService {

    @Inject
    private ScrapService scrapService;

    @Inject
    private ZipService zipService;

    @Inject
    private ChapterService chapterService;

    @Inject
    private ChapterPersistenceService chapterPersistenceService;

    @Inject
    private FilePersistenceService filePersistenceService;

    @Inject
    private MangaSubscriptionService mangaSubscriptionService;

    @Inject
    private MailService mailService;

    private Chapter getLastChapter(final Manga manga) {
        if (manga != null && manga.getId() != null) {
            final Page<Chapter> all = chapterService.findByMangaId(0, 1, manga.getId());
            return all.getContent().get(0);
        }
        return null;
    }

    public void transformOneChapter(final Manga manga) {
        final Chapter lastChapter = getLastChapter(manga);
        final int chapterNumber = lastChapter.getNum() + 1;

        // we scrap one
        final ChapterDto chapterXhtml = scrapService.scrapOne(manga, chapterNumber);
        final String name = chapterXhtml.getFileName().split(".")[0] + ".epub";
        // create the zip
        final File epub = zipService.zipFile(name, Collections.singletonList(chapterXhtml.getFile()));
        final getln.data.Entity.File file = new getln.data.Entity.File();
        file.setName(epub.getName());
        file.setType("epub");
        file.setUrl(epub.getPath());

        chapterXhtml.getFile().delete();

        // Create the File
        final getln.data.Entity.File epubSql = filePersistenceService.save(file);
        final Chapter newChapter = new Chapter();
        newChapter.setNum(chapterNumber);
        newChapter.setFile(epubSql);
        newChapter.setTitle(chapterXhtml.getName());
        chapterPersistenceService.save(newChapter);

        //need to send chapter to all user who subscribe
        final Iterable<MangaSubscription> all = mangaSubscriptionService.findByMangaId(manga.getId());
        all.forEach(mangaSubscription -> {
            //then send mail
            mailService.sendMail(mangaSubscription.getUser().getEmail(), epub.getPath(), epub.getName());
        });

    }
}

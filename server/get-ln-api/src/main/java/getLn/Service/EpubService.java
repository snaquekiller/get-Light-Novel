package getLn.Service;

import java.io.File;
import java.util.Collections;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QSort;
import org.springframework.stereotype.Service;

import get.ln.data.Chapter;
import get.ln.data.ChapterPersistenceService;
import get.ln.data.FilePersistenceService;
import get.ln.data.Manga;
import get.ln.data.MangaSubscription;
import get.ln.data.MangaSubscriptionService;
import get.ln.data.QChapter;
import get.ln.data.QMangaSubscription;
import getLn.model.ChapterDto;

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
    private ChapterPersistenceService chapterPersistenceService;

    @Inject
    private FilePersistenceService filePersistenceService;

    @Inject
    private MangaSubscriptionService mangaSubscriptionService;

    @Inject
    private MailService mailService;

    private Chapter getLastChapter(final Manga manga) {
        if (manga != null && manga.getId() != null) {
            final Pageable page = new PageRequest(0, 1, new QSort(QChapter.chapter.creationDate.asc()));
            final Page<Chapter> all = chapterPersistenceService.findAll(QChapter.chapter.manga.id.eq(manga.getId()), page);
            return all.getContent().get(0);
        }
        return null;
    }

    public void transformOneChapter(Manga manga) {
        final Chapter lastChapter = getLastChapter(manga);
        final int chapterNumber = lastChapter.getNum() + 1;

        // we scrap one
        final ChapterDto chapterXhtml = scrapService.scrapOne(manga, chapterNumber);
        final String name = chapterXhtml.getFileName().split(".")[0] + ".epub";
        // create the zip
        final File epub = zipService.zipFile(name, Collections.singletonList(chapterXhtml.getFile()));
        final get.ln.data.File file = new get.ln.data.File();
        file.setName(epub.getName());
        file.setType("epub");
        file.setUrl(epub.getPath());

        // Create the File
        final get.ln.data.File epubSql = filePersistenceService.save(file);
        final Chapter newChapter = new Chapter();
        newChapter.setNum(chapterNumber);
        newChapter.setFile(epubSql);
        newChapter.setTitle(chapterXhtml.getName());
        chapterPersistenceService.save(newChapter);

        //need to send chapter to all user who subscribe
        final Iterable<MangaSubscription> all =
            mangaSubscriptionService.findAll(QMangaSubscription.mangaSubscription.manga.id.eq(manga.getId()));
        all.forEach(mangaSubscription -> {
            //then send mail
            mailService.sendMail(mangaSubscription.getUser().getEmail(), epub.getPath(), epub.getName());
        });

    }
}

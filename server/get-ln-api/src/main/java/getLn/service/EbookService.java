package getLn.service;

import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.path;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import getLn.model.ChapterDto;
import getln.data.entity.BOOK_FORMAT;
import getln.data.entity.Chapter;
import getln.data.entity.FileStorage;
import getln.data.entity.Manga;
import getln.data.entity.MangaSubscription;
import getln.data.service.ChapterPersistenceService;
import getln.data.service.FilePersistenceService;
import getln.service.common.ChapterService;
import getln.service.common.MangaSubscriptionService;

/**
 * .
 */
@Service
public class EbookService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EbookService.class);

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

    @Inject
    private MobiService mobiService;

    private Chapter getLastChapter(final Manga manga) {
        LOGGER.info("get the last chapter for manga={}", manga);
        if (manga != null && manga.getId() != null) {
            final Page<Chapter> all = chapterService.findLastChapter(manga.getId());
            final List<Chapter> content = all.getContent();
            if (!content.isEmpty()) {
                LOGGER.info("Found the last chapter for manga={} chapter={}", manga, content.get(0));
                return content.get(0);
            }
        }
        return null;
    }

    public void transformOneChapter(final Manga manga) throws Exception {
        final Chapter lastChapter = getLastChapter(manga);
        int chapterNumber = 1;
        LOGGER.info("chaper = {}", lastChapter);
        if (lastChapter != null) {
            chapterNumber = lastChapter.getNum() + 1;
        }
        // we scrap one
        final ChapterDto chapterXhtml = scrapService.scrapOne(manga, chapterNumber);
        if (chapterXhtml == null) {
            throw new Exception("Can't scrap");
        }
        //        LOGGER.error("chapter ={}", chapterXhtml);
        final String name = chapterXhtml.getFileName().split("\\.")[0] + ".epub";

        // create the zip
        final File epub = zipService.zipFile(chapterXhtml.getFilePath() + name, chapterXhtml.getFile());
        FileStorage personalEpub = new FileStorage(epub.getName(), BOOK_FORMAT.EPUB.name(), epub.getPath());
        personalEpub = filePersistenceService.save(personalEpub);

        final File mobi = mobiService.epubToMbi(epub);
        FileStorage mobiPeronalFile = new FileStorage(mobi.getName(), BOOK_FORMAT.MOBI.name(), mobi.getPath());
        mobiPeronalFile = filePersistenceService.save(mobiPeronalFile);

        //        chapterXhtml.getFile().delete();

        // Create the File
        final Set<FileStorage> files = new HashSet<>();
        files.add(personalEpub);
        files.add(mobiPeronalFile);

        final Chapter newChapter = new Chapter();
        newChapter.setNum(chapterNumber);
        newChapter.setFiles(files);
        newChapter.setTitle(chapterXhtml.getName());
        newChapter.setManga(manga);
        chapterPersistenceService.save(newChapter);

        //need to send chapter to all user who subscribe
        final Iterable<MangaSubscription> all = mangaSubscriptionService.findByMangaId(manga.getId());
        all.forEach(mangaSubscription -> {
            File send = null;
            if (mangaSubscription.getFormat().compareTo(BOOK_FORMAT.EPUB) == 0) {
                //then send mail
                send = epub;
            } else if (mangaSubscription.getFormat().compareTo(BOOK_FORMAT.MOBI) == 0) {
                send = mobi;
            }
            if (path != null) {
                mailService.sendMail(mangaSubscription.getUser().getEmail(), send.getPath(), send.getName());
            }
        });

    }
}

package getLn.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import getLn.model.ChapterDto;
import getLn.service.ebook.EpubService;
import getLn.service.ebook.MobiService;
import getLn.service.ebook.ZipService;
import getLn.service.scrap.ScrapLnNovelService;
import getLn.service.scrap.ScrapMngDoomService;
import getln.data.entity.BOOK_FORMAT;
import getln.data.entity.BOOK_TYPE;
import getln.data.entity.Chapter;
import getln.data.entity.FileStorage;
import getln.data.entity.Manga;
import getln.data.entity.MangaSubscription;
import getln.data.service.ChapterPersistenceService;
import getln.data.service.FilePersistenceService;
import getln.service.common.ChapterSqlService;
import getln.service.common.MangaSubscriptionSqlService;

import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.path;

/**
 * .
 */
@Service
public class EbookService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EbookService.class);

    @Inject
    private EpubService epubService;

    @Inject
    private ZipService zipService;

    @Inject
    private ChapterSqlService chapterService;

    @Inject
    private ChapterPersistenceService chapterPersistenceService;

    @Inject
    private FilePersistenceService filePersistenceService;

    @Inject
    private MangaSubscriptionSqlService mangaSubscriptionService;

    @Inject
    private MailService mailService;

    @Inject
    private ScrapMngDoomService scrapMngDoom;

    @Inject
    private ScrapLnNovelService scrapLnNovelService;

    @Inject
    private MobiService mobiService;


    public /*static */ void main() throws IOException {
        String BOOK_NAME = "Douluo Dalu 3";
        if (false) {
            String title = "";
            final List<ChapterDto> chapters = new ArrayList<ChapterDto>();
            for (int i = 1066; i < 1068; i++) {
                try {
                    final String bookWithoutSpecialChar = BOOK_NAME.replaceAll("\\W", "_");
                    final String fileName = String.format("%s_%d.xhtml", bookWithoutSpecialChar, i);
                    ChapterDto chapter = new ChapterDto(String.format("%s", bookWithoutSpecialChar), i, BOOK_NAME,
                            fileName);
                    chapter =
                            scrapLnNovelService.addTextAndTitleLNMTL(i, chapter,
                                    "https://lnmtl.com/chapter/douluo-dalu-3-dragon-king-s-legend-chapter-");
                    List<File> files = epubService.writeChapter(BOOK_NAME, chapter);
                    chapter.setFile(files);
                    title += String
                            .format("\t<li>\n\t<a href=\"%s#%d\">%s</a>\n</li>\n", fileName, i, chapter.getName());
                    chapters.add(chapter);
                } catch (final Exception e) {
                    System.err.println("Chapter  " + i + "not found ");
                }
            }
            epubService.createTableMatiere(BOOK_NAME, title);
            epubService.createTOX(BOOK_NAME, chapters);
        }
    }


    public ChapterDto scrapOne(final Manga manga, final double chapterNumber) {
        try {
            final String bookWithoutSpecialChar = manga.getBookNameWithoutSpecialChar();
            final String fileName = String.format("%s_%d.xhtml", bookWithoutSpecialChar, chapterNumber);
            final String bookName = manga.getName();
            ChapterDto chapter =
                    new ChapterDto(String.format("%s/%s", this.epubService.DIRECTORY, bookWithoutSpecialChar),
                            chapterNumber, bookName, fileName);
            if (manga.getType().equals(BOOK_TYPE.LIGHT_NOVEL)) {
                chapter = this.scrapLnNovelService.addTextAndTitleLNMTL(chapterNumber, chapter, manga.getUrl());
                List<File> files = epubService.writeChapter(bookName, chapter);
                chapter.setFile(files);
            } else {
                chapter = scrapMngDoom.chapter(manga.getUrl(), manga, chapterNumber, chapter);
            }
            return chapter;

        } catch (final Exception e) {
            LOGGER.error("Chapter  " + chapterNumber + "not found ", e);
        }
        return null;
    }

    public void transformOneChapter(final Manga manga) throws Exception {
        final Chapter lastChapter = getLastChapter(manga);
        double chapterNumber = 1d;
        LOGGER.info("chaper = {}", lastChapter);
        if (lastChapter != null) {
            chapterNumber = lastChapter.getNum() + 1d;
        }
        // we scrap one
        final ChapterDto chapterXhtml = scrapOne(manga, chapterNumber);
        if (chapterXhtml == null) {
            throw new Exception("Can't scrap");
        }
        //        LOGGER.error("chapter ={}", chapterXhtml);
        final String name = chapterXhtml.getFileName().split("\\.")[0] + ".epub";

        // create the zip
        final File epub = this.zipService.zipFile(chapterXhtml.getFilePath() + name, chapterXhtml.getFile());
        FileStorage personalEpub = new FileStorage(epub.getName(), BOOK_FORMAT.EPUB.name(), epub.getPath());
        personalEpub = this.filePersistenceService.save(personalEpub);

        final File mobi = this.mobiService.epubToMbi(epub);
        FileStorage mobiPeronalFile = new FileStorage(mobi.getName(), BOOK_FORMAT.MOBI.name(), mobi.getPath());
        mobiPeronalFile = this.filePersistenceService.save(mobiPeronalFile);

        chapterXhtml.getFile().forEach(File::deleteOnExit);

        // Create the File
        final Set<FileStorage> files = new HashSet<>();
        files.add(personalEpub);
        files.add(mobiPeronalFile);

        final Chapter newChapter = new Chapter(manga);
        newChapter.setNum(chapterNumber);
        newChapter.setFiles(files);
        newChapter.setTitle(chapterXhtml.getName());
        this.chapterPersistenceService.save(newChapter);

        //need to send chapter to all user who subscribe
        final Iterable<MangaSubscription> all = this.mangaSubscriptionService.findByMangaId(manga.getId());
        all.forEach(mangaSubscription -> {
            File send = null;
            if (0 == mangaSubscription.getFormat().compareTo(BOOK_FORMAT.EPUB)) {
                //then send mail
                send = epub;
            } else if (0 == mangaSubscription.getFormat().compareTo(BOOK_FORMAT.MOBI)) {
                send = mobi;
            }
            if (null != path) {
                this.mailService.sendMail(mangaSubscription.getUser().getEmail(), send.getPath(), send.getName());
            }
        });

    }

    /**
     * @param manga
     * @return
     */
    private Chapter getLastChapter(final Manga manga) {
        LOGGER.info("get the last chapter for manga={}", manga);
        if ((manga != null) && (manga.getId() != null)) {
            final Page<Chapter> all = this.chapterService.findLastChapter(manga.getId());
            final List<Chapter> content = all.getContent();
            if (!content.isEmpty()) {
                LOGGER.info("Found the last chapter for manga={} chapter={}", manga, content.get(0));
                return content.get(0);
            }
        }
        return null;
    }
}

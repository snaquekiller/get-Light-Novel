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
import get.ln.data.QChapter;
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
        final ChapterDto chapterXhtml = scrapService.scrapOne(manga, chapterNumber);
        final String name = chapterXhtml.getFileName().split(".")[0] + ".epub";
        final File epub = zipService.zipFile(name, Collections.singletonList(chapterXhtml.getFile()));
        final get.ln.data.File file = new get.ln.data.File();
        file.setUrl(epub.getName());
        file.setType("epub");
        file.setUrl(epub.getPath());
        
        final get.ln.data.File epubSql = filePersistenceService.save(file);
        final Chapter newChapter = new Chapter();
        newChapter.setNum(chapterNumber);
        newChapter.setFile(epubSql);
        newChapter.setTitle(chapterXhtml.getName());
        chapterPersistenceService.save(newChapter);

    }
}

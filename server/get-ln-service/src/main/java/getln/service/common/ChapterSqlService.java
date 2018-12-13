package getln.service.common;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QSort;
import org.springframework.stereotype.Service;

import getln.data.entity.Chapter;
import getln.data.entity.QChapter;
import getln.data.service.ChapterPersistenceService;

/**
 * .
 */
@Service
public class ChapterSqlService {

    @Inject
    private ChapterPersistenceService chapterPersistenceService;

    public Page<Chapter> findByMangaId(final int page, final int size, final Long id) {
        final Pageable pageable = new PageRequest(page, size, new QSort(QChapter.chapter.creationDate.desc()));
        final Page<Chapter> all = chapterPersistenceService.findAll(QChapter.chapter.manga.id.eq(id), pageable);
        return all;
    }

    public Page<Chapter> findLastChapter(final Long mangaId) {
        final Pageable pageable = new PageRequest(0, 1, new QSort(QChapter.chapter.num.desc()));
        final Page<Chapter> all = chapterPersistenceService.findAll(QChapter.chapter.manga.id.eq(mangaId), pageable);
        return all;
    }
}

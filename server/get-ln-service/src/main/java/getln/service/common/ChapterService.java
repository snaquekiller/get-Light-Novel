package getln.service.common;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QSort;
import org.springframework.stereotype.Service;

import getln.data.Entity.Chapter;
import getln.data.Entity.QChapter;
import getln.data.Service.ChapterPersistenceService;

/**
 * .
 */
@Service
public class ChapterService {

    @Inject
    private ChapterPersistenceService chapterPersistenceService;

    public Page<Chapter> findByMangaId(final int page, final int size, final Long id) {
        final Pageable pageable = new PageRequest(0, 1, new QSort(QChapter.chapter.creationDate.asc()));
        final Page<Chapter> all = chapterPersistenceService.findAll(QChapter.chapter.manga.id.eq(id), pageable);
        return all;
    }
}
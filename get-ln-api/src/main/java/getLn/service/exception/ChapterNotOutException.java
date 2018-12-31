package getLn.service.exception;

import getLn.model.ChapterDto;
import lombok.Data;
import lombok.ToString;

/**
 * .
 */
@Data
@ToString
public class ChapterNotOutException extends Exception {

    private ChapterDto chapter;

    public ChapterNotOutException(String message, ChapterDto chapterDto) {
        super(message);
        chapter = chapterDto;
    }


    public ChapterNotOutException(String message, Throwable cause, ChapterDto chapterDto) {
        super(message, cause);
        chapter = chapterDto;
    }
}

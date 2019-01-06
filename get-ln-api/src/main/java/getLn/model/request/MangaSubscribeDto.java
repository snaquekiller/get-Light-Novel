package getLn.model.request;

import getln.data.entity.BOOK_FORMAT;
import getln.data.entity.BOOK_TYPE;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.StringJoiner;

@lombok.Data
public class MangaSubscribeDto implements Serializable {

    @NotNull
    private Long userId;

    @NotNull
    private String chapterNum;

    @NotNull
    private BOOK_FORMAT bookFormat;
}

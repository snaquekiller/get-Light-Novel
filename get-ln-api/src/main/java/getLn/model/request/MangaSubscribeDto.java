package getLn.model.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import getln.data.entity.BOOK_FORMAT;

@lombok.Data
public class MangaSubscribeDto implements Serializable {

    @NotNull
    private String chapterNum;

    @NotNull
    private BOOK_FORMAT bookFormat;
}

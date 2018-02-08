package getLn.model.request;

import java.io.Serializable;
import java.util.StringJoiner;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

public class MangaSubscriptionRequestDto implements Serializable {

    /**
     * The name of the of the manga
     */
    @NotBlank
    private String name;

    /**
     * The manga
     */
    @NotBlank
    @Min(1)
    private Long mangaId;

    @Override
    public String toString() {
        return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]").add("name = " + name)
            .add("mangaId = " + mangaId).toString();
    }

    /**
     * Gets The name of the of the manga.
     *
     * @return Value of The name of the of the manga.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets The manga.
     *
     * @return Value of The manga.
     */
    public Long getMangaId() {
        return mangaId;
    }

    /**
     * Sets new The manga.
     *
     * @param mangaId New value of The manga.
     */
    public void setMangaId(final Long mangaId) {
        this.mangaId = mangaId;
    }

    /**
     * Sets new The name of the of the manga.
     *
     * @param name New value of The name of the of the manga.
     */
    public void setName(final String name) {
        this.name = name;
    }
}

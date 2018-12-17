package getLn.model.request;

import java.io.Serializable;
import java.util.StringJoiner;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

@lombok.Data
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

}

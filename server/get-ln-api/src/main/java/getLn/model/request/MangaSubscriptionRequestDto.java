package getLn.model.request;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import java.io.Serializable;


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

}

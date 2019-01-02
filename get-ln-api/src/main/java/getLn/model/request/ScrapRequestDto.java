package getLn.model.request;

import javax.validation.constraints.NotNull;

/**
 * .
 */
@lombok.Data
public class ScrapRequestDto {

    @NotNull
    private String url;

}

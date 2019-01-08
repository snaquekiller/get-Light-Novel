package getLn.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Represent a sub-part of a paginated response.
 * T is the type of contained payload.
 */
@JsonInclude(Include.ALWAYS)
@ResponseBody
@lombok.Data
public class PaginatedResponse<T> implements Serializable {

    /** The default serial version UID. */
    private static final long serialVersionUID = 1L;

    /** The page. */
    @JsonProperty
    private ResponseMetadata metadata;

    /** The data payload. */
    @JsonUnwrapped
    private List<T> payload;

    public PaginatedResponse() {
    }

    public PaginatedResponse(ResponseMetadata metadata, List<T> payload) {
        this.metadata = metadata;
        this.payload = payload;
    }

    public PaginatedResponse(Page<T> page,String search) {
        this.metadata = new ResponseMetadataBuilder().parse(page).search(search).build();
        this.payload = page.getContent();
    }
}

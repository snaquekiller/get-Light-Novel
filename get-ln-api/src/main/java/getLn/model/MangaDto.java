package getLn.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * .
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MangaDto {

    private List<String> chapters;

    private String author;

    private String name;

    private String image;

    private String description;
}

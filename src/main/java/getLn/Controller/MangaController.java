package getLn.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * .
 */
@RestController
public class MangaController {

    /**
     * List reviewers.
     *
     * @return the reviewers response
     */
    @RequestMapping(value = "/manga", method = RequestMethod.GET)
    public String listReviewers() {
        // @formatter:on

        return "coucou";
    }

    /**
     * List reviewers.
     *
     * @return the reviewers response
     */
    @RequestMapping(value = "/chapter", method = RequestMethod.GET)
    public String listRevdsqdiewers() {
        // @formatter:on

        return "pascoucocu";
    }

}

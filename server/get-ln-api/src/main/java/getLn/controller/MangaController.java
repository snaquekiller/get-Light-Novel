package getLn.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public String addUser(@RequestParam(required = true, defaultValue = "nom") final String nom) {
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

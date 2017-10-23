package getLn.controller;

import javax.inject.Qualifier;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Interface ApiDataControllerInterface.
 */
@Qualifier
@RestController
@RequestMapping(value = "/")
public @interface ApiControllerInterface {

}

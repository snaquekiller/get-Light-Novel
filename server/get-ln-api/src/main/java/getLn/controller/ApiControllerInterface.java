package getLn.controller;

import javax.inject.Qualifier;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Interface ApiDataControllerInterface.
 */
@Qualifier
@RestController
@RequestMapping(value = "/")
@ComponentScan(basePackageClasses = ApiControllerInterface.class)
public @interface ApiControllerInterface {

}

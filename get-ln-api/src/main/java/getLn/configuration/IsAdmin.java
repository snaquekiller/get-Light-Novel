package getLn.configuration;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

import org.springframework.security.access.prepost.PreAuthorize;

/**
 * .
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
@PreAuthorize("hasRole('ROLE_ADMIN')")
public @interface IsAdmin {

}

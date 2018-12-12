package getln.data.entity;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

import org.springframework.context.annotation.ComponentScan;

import getln.data.LnData;

/**
 * .
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
@ComponentScan(
        basePackageClasses = {LnData.class}
)
public @interface EntityData {

}

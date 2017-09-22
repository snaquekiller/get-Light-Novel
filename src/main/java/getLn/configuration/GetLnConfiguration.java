package getLn.configuration;

import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.EnvironmentInfoContributor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

import getLn.GetLnJob;
import getLn.data.Data;

//@formatter:off
@Configuration
@PropertySource("classpath:spring/prd.properties")
@Import({ JpaConfiguration.class })
@ComponentScan(basePackageClasses = {
    GetLnJob.class,
    Data.class,
    })
//@formatter:on
public class GetLnConfiguration {

    /** The env. */
    @Inject
    private Environment env;

    /** The context. */
    @Inject
    private ServletContext context;

    /**
     * Returns the property sources placeholder configurator.
     *
     * @return The property sources placeholder configurator.
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    /**
     * @param environment
     * @return
     */
    @Autowired
    public EnvironmentInfoContributor environmentInfoContributor(final ConfigurableEnvironment environment) {
        return new EnvironmentInfoContributor(environment);
    }

}

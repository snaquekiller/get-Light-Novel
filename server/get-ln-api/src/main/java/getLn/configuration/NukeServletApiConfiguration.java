package getLn.configuration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import getLn.controller.ApiControllerInterface;

/**
 * API servlet configuration.
 */
@Configuration
@EnableWebMvc
@EnableAsync
@ComponentScan(basePackageClasses = {ApiControllerInterface.class})
public class NukeServletApiConfiguration extends WebMvcConfigurerAdapter {

    /**
     * Checkstyle hack.
     */
    public void checkstyle() {
        // Yes, this is a hack to avoid checkstyle flagging this class as an
        // "utility class" because it only has static
        // methods, and requesting to remove the public default constructor. It
        // would prevent spring from instantiating
        // the class.
    }

    /**
     * Returns the PropertySourcesPlaceholderConfigurer.
     *
     * @return The PropertySourcesPlaceholderConfigurer.
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    /**
     * Resolver.
     *
     * @return the standard servlet multipart resolver
     */
    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

    @Override
    public void configureDefaultServletHandling(final DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public StringHttpMessageConverter stringMessageConverter() {
        return new StringHttpMessageConverter();
    }

    /**
     * Jackson builder.
     *
     * @return the jackson 2 object mapper builder
     */
    @Bean
    public Jackson2ObjectMapperBuilder jacksonBuilder() {
        final Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.serializationInclusion(Include.NON_NULL);
        builder.propertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE);
        builder.serializationInclusion(Include.NON_EMPTY);
        // Avec millisecondes "yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
        builder.indentOutput(true).dateFormat(dateFormat);
        return builder;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
     * #configureMessageConverters(java.util.List)
     */

    @Override
    public void configureMessageConverters(final List<HttpMessageConverter<?>> converters) {
        converters.add(new MappingJackson2HttpMessageConverter(jacksonBuilder().build()));
        converters.add(stringMessageConverter());
    }

}

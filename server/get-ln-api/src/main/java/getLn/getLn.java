package getLn;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

import getLn.Service.ScrapService;
import getLn.configuration.GetLnConfiguration;

/**
 * @author Nicolas
 */
@Configuration
@Import({GetLnConfiguration.class})
@SpringBootApplication
@EnableScheduling
public class getLn {

    private static final String ligh_novel = "douluo-dalu-3-dragon-king-s-legend-chapter-";

    private static final String BOOK_NAME = "Douluo Dalu 3";

    @Inject
    private static ScrapService scrapService;

    /**
     * @param args the command line arguments
     */
    public static void main(final String[] args) throws IOException {
        SpringApplication.run(getLn.class);
        scrapService.main();
    }



}

package getLn;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

import getLn.configuration.GetLnConfiguration;
import getLn.service.EbookService;

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

    /**
     * @param args the command line arguments
     */
    public static void main(final String[] args) throws IOException {
        SpringApplication.run(getLn.class);
        final EbookService epubService = new EbookService();
        epubService.main();
    }

}

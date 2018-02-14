package getLn.service;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * .
 */
@Service
public class MobiService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MobiService.class);

    public File epubToMbi(final File file) {
        LOGGER.info("try to create mobi  for File={}", file);
        try {
            final Process exec = Runtime.getRuntime().exec("kindle/kindlegen " + file.getPath());
            exec.waitFor();
            return new File(file.getPath().replace(".epub", ".mobi"));
        } catch (final IOException | InterruptedException e) {
            LOGGER.error("Can't create the epub File={}", file);
            return null;
        }
    }

}

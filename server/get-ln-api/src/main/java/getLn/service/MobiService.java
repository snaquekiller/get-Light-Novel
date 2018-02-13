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
        LOGGER.info("try to create epub  for File={}", file);
        try {
            Runtime.getRuntime().exec("kindle/kindlegen " + file.getPath());
            return new File(file.getPath().replace(".epub", ".mobi"));
        } catch (final IOException e) {
            LOGGER.error("Can't create the epub File={}", file);
            return null;
        }
    }

}

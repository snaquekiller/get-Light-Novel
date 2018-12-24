package getLn.service.ebook;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * .
 */
@Slf4j
@Service
public class MobiService {


    /**
     * Create a Mobi file from an epub File.
     *
     * @param file
     * @return
     */
    public File epubToMbi(final File file) {
        log.info("try to create mobi  for File={}", file);
        try {
            final Process exec = Runtime.getRuntime().exec("kindle/kindlegen " + file.getPath());
            exec.waitFor();
            return new File(file.getPath().replace(".epub", ".mobi"));
        } catch (final IOException | InterruptedException e) {
            log.error("Can't create the mobi File={}", file, e);
            return null;
        }
    }

}

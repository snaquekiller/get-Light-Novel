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


    public void epubToMbi(File file){
        try {
            Runtime.getRuntime().exec("kindle/kindlegen "+file.getPath());
        } catch (IOException e) {
            LOGGER.error("Can't create the epub File={}", file);
        }
    }




}

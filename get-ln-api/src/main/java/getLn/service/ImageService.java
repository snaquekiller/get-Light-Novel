package getLn.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * .
 */
@Slf4j
@Service
public class ImageService {

    private final static int MAX_HEIGHT = 1500;//(px)
    private final static int CUT_HEIGHT = 1300;//(px)

    public List<File> splitImage(File file) throws IOException {
        final BufferedImage source = ImageIO.read(file);
        int idx = 0;
        if(source.getHeight() < MAX_HEIGHT) {
            log.info("don't need to split it");
            return Collections.singletonList(file);
        }
        String path = file.getParent();
        String name = file.getName().split(".jpg")[0];
        ArrayList<File> files = new ArrayList<>();
        for (int y = 0; y < source.getHeight(); y += CUT_HEIGHT) {
            File output = new File(
                    path +"/" + name + "_" + idx++ + ".jpg");
            files.add(output);
            int cut_height = y + CUT_HEIGHT > source.getHeight() ? source.getHeight() - y : CUT_HEIGHT;
            ImageIO.write(source.getSubimage(0, y, source.getWidth(),cut_height ), "jpg", output);
        }
        file.delete();

        return files;
    }
}

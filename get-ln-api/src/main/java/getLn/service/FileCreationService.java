package getLn.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.springframework.stereotype.Service;

/**
 * .
 */
@Service
public class FileCreationService {

    private static final String DIRECTORY = "data";

    public FileOutputStream fileStream(String directory, String name) throws FileNotFoundException {
        String filePath = String.format("%s/%s/%s", DIRECTORY, directory, name);
        final FileOutputStream fos = new FileOutputStream(filePath);
        return fos;
    }

    public File createFile(String directory, String name) {
        return new File(String.format("%s/%s/%s", DIRECTORY, directory, name));
    }
}

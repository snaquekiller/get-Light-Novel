package getLn.service.ebook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import getLn.service.FileCreationService;
import lombok.extern.slf4j.Slf4j;

/**
 * .
 */
@Slf4j
@Service
public class ZipService {

    @Inject
    private FileCreationService fileCreationService;


    public void addToZipFile(File file, final ZipOutputStream zos) {
        log.debug(String.format("Writing '%s' to zip file", file.getName()));

        try (FileInputStream fis = new FileInputStream(file)) {
            final ZipEntry zipEntry = new ZipEntry(file.getName());
            zos.putNextEntry(zipEntry);

            final byte[] bytes = new byte[1024];
            int length;
            while ((length = fis.read(bytes)) >= 0) {
                zos.write(bytes, 0, length);
            }

            zos.closeEntry();
            fis.close();
        } catch (final IOException e) {
            log.error("Can't add the file={}", file.getName(), e);
        }

    }

    /**
     * Can put all file into one compress file. The extension will depend on the ZipName
     *
     * @param fileName
     * @param files
     * @return
     */
    public File zipFile(String directory, String fileName, final List<File> files) {

        try {
            final FileOutputStream fos = fileCreationService.fileStream(directory, fileName);
            try (ZipOutputStream zos = new ZipOutputStream(fos)) {
                files.forEach(file -> addToZipFile(file, zos));
                zos.close();
            }
            fos.close();
            return fileCreationService.createFile(directory, fileName);
        } catch (final IOException e) {
            log.error("Can't create the zip d={} name={}", directory, fileName, e);
        }
        return null;
    }

}

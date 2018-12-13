package getLn.service.ebook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * .
 */
@Service
public class ZipService {

    /**
     * The logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ZipService.class);

    public void addToZipFile(final String fileName, final ZipOutputStream zos) throws IOException {
        LOGGER.debug(String.format("Writing '%s' to zip file", fileName));

        final File file = new File(fileName);
        final FileInputStream fis = new FileInputStream(file);
        final ZipEntry zipEntry = new ZipEntry(fileName);
        zos.putNextEntry(zipEntry);

        final byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zos.write(bytes, 0, length);
        }

        zos.closeEntry();
        fis.close();
    }

    /**
     * Can put all file into one compress file. The extension will depend on the ZipName
     *
     * @param zipName
     * @param files
     * @return
     */
    public File zipFile(final String zipName, final List<File> files) {

        try {
            final FileOutputStream fos = new FileOutputStream(zipName);
            try (ZipOutputStream zos = new ZipOutputStream(fos)) {

                files.forEach(file -> {
                    try {
                        addToZipFile(file.getAbsolutePath(), zos);
                    } catch (final IOException e) {
                        LOGGER.error("Can't add the file={} to the zipname={}", file.getName(), zipName, e);
                    }
                });
                zos.close();
            }
            fos.close();
            return new File(zipName);
        } catch (final IOException e) {
            LOGGER.error("Can't create the zip={}", zipName, e);
        }
        return null;
    }

}

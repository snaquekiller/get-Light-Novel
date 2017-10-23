package getLn.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * .
 */
@RestController
public class MangaController {

    @Inject
    private Environment environment;

    public static void addToZipFile(final String fileName, final ZipOutputStream zos) throws FileNotFoundException, IOException {

        System.out.println("Writing '" + fileName + "' to zip file");

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
     * List reviewers.
     *
     * @return the reviewers response
     */
    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public String addUser(@RequestParam(required = true, defaultValue = "nom") final String nom) {
        // @formatter:on

        return "coucou";
    }

    /**
     * List reviewers.
     *
     * @return the reviewers response
     */
    @RequestMapping(value = "/chapter", method = RequestMethod.GET)
    public String listRevdsqdiewers() {
        // @formatter:on

        return "pascoucocu";
    }

    public void main(final String[] args) {

        try {
            final FileOutputStream fos = new FileOutputStream("atest.zip");
            final ZipOutputStream zos = new ZipOutputStream(fos);

            final String file1Name = "file1.txt";
            final String file2Name = "file2.txt";
            final String file3Name = "folder/file3.txt";
            final String file4Name = "folder/file4.txt";
            final String file5Name = "f1/f2/f3/file5.txt";

            addToZipFile(file1Name, zos);
            addToZipFile(file2Name, zos);
            addToZipFile(file3Name, zos);
            addToZipFile(file4Name, zos);
            addToZipFile(file5Name, zos);

            zos.close();
            fos.close();

        } catch (final FileNotFoundException e) {
            e.printStackTrace();
        } catch (final IOException e) {
            e.printStackTrace();
        }

    }

    private void sendMail() {
        // Recipient's email ID needs to be mentioned.
        final String to = "nic.guitton@gmail.com";

        // Sender's email ID needs to be mentioned
        final String from = "nic.guitton@gmail.com";

        // Assuming you are sending email from localhost
        final String host = environment.getProperty("postfix.url");

        // Get system properties
        final Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);

        // Get the default Session object.
        final Session session = Session.getDefaultInstance(properties);

        try {
            // Create a default MimeMessage object.
            final MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("This is the Subject Line!");

            // Now set the actual message
            message.setText("This is actual message");

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (final MessagingException mex) {
            mex.printStackTrace();
        }
    }

}

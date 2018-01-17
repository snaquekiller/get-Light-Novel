package getLn.service;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * .
 */
@Service
public class MailService {

    @Inject
    private Environment environment;

    /** The logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(MailService.class);

    public void sendMail(final String emailTo, final String pathFile, final String fileName) {
        // Recipient's email ID needs to be mentioned.
        //        final String to = "nic.guitton@gmail.com";

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
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));

            // Set Subject: header field
            message.setSubject("This is the Subject Line!");

            // Now set the actual message
            message.setText("This is actual message");

            // Set the File body
            final Multipart multipart = attachementFile(pathFile, fileName);
            message.setContent(multipart);

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (final MessagingException mex) {
            LOGGER.error("Can't send message email ", mex);
        }
    }

    private Multipart attachementFile(final String pathFile, final String fileName) throws MessagingException {
        final Multipart multipart = new MimeMultipart();

        final MimeBodyPart messageBodyPart = new MimeBodyPart();
        final DataSource source = new FileDataSource(pathFile);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(fileName);
        multipart.addBodyPart(messageBodyPart);
        return multipart;
    }
}

package getLn.Controller;

import java.util.Properties;

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
import org.springframework.web.bind.annotation.RestController;

/**
 * .
 */
@RestController
public class MangaController {

    @Inject
    private Environment environment;

    /**
     * List reviewers.
     *
     * @return the reviewers response
     */
    @RequestMapping(value = "/manga", method = RequestMethod.GET)
    public String listReviewers() {
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

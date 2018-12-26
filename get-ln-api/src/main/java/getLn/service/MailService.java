package getLn.service;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * .
 */
@Slf4j
@Service
public class MailService {

    @Autowired
    public JavaMailSender emailSender;

    public void sendSimpleMessage(
            String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    public void sendMail(final String emailTo, final String pathFile, final String fileName)
            throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(emailTo);
        helper.setSubject(String.format("Your New Story came : %s", fileName));
        helper.setText("Here your story");

        FileSystemResource file
                = new FileSystemResource(new File(pathFile));
        helper.addAttachment(fileName, file);

        emailSender.send(message);
    }
}


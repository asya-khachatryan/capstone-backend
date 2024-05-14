package edu.aua.common.service;

import edu.aua.common.model.EmailDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;


@Service
@Slf4j
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender javaMailSender;

    @Value("${email.from.address}")
    private String fromName;

    @Value("${email.sender.username}")
    private String fromEmail;

    public EmailServiceImpl(final JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(final EmailDTO emailDTO) {
        log.info("Started sendEmail");
        final MimeMessage message = buildMailMessage(emailDTO);
        javaMailSender.send(message);
        log.info("Finished sendEmail");
    }

    @Override
    public void sendEmailHtml(EmailDTO emailDTO) {
        log.info("Started sendEmail with html body {}", emailDTO);
        final MimeMessage msg = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(msg);
            helper.setTo(emailDTO.getEmailTo());
            helper.setSubject(emailDTO.getSubject());
            helper.setText(emailDTO.getText(), true);
        } catch (MessagingException e) {
            log.error("Error when trying to attach file", e);
            throw new RuntimeException(e.getMessage());
        }
        javaMailSender.send(msg);
        log.info("Finished sendEmail with html body");
    }

    @Override
    public void sendMailWithAttachments(final EmailDTO emailDTO, MultipartFile multipart) {
        log.info("In sendMailWithAttachment");
        MimeMessage msg = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            helper.setTo(emailDTO.getEmailTo());
            helper.setSubject(emailDTO.getSubject());
            helper.setText(emailDTO.getText(), true);
            helper.addAttachment(multipart.getName(), multipart);
            log.debug("Added a file attachment: {}", multipart.getName());
        } catch (MessagingException e) {
            log.error("Error when trying to attach file", e);
            throw new RuntimeException(e.getMessage());
        }
        javaMailSender.send(msg);
    }

    private MimeMessage buildMailMessage(final EmailDTO emailDTO) {
        log.info("Started creating mail message");
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, false);
            helper.setFrom(new InternetAddress(fromEmail, fromName));
            helper.setTo(emailDTO.getEmailTo());
            helper.setSubject(emailDTO.getSubject());
            helper.setText(emailDTO.getText());
            log.info("Finished creating mail message");
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return message;
    }
}

package edu.aua.common.service;

import edu.aua.common.model.EmailDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
@Slf4j
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender javaMailSender;

    @Value("${email.from.address}")
    private String setFrom;

    public EmailServiceImpl(final JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(final EmailDTO emailDTO) {
        log.info("Started sendEmail");
        final SimpleMailMessage simpleMailMessage = buildMailMessage(emailDTO);
        javaMailSender.send(simpleMailMessage);
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
        } catch (
                MessagingException e) {
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

    private SimpleMailMessage buildMailMessage(final EmailDTO emailDTO) {
        log.info("Started creating mail message");
        final SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(setFrom);
        simpleMailMessage.setTo(emailDTO.getEmailTo());
        simpleMailMessage.setSubject(emailDTO.getSubject());
        simpleMailMessage.setText(emailDTO.getText());
        log.info("Finished creating mail message");
        return simpleMailMessage;
    }
}

package edu.aua.common.service;

import edu.aua.common.model.Mail;
import org.springframework.web.multipart.MultipartFile;

public interface EmailService {

    void sendEmail(Mail mail);

    void sendEmailHtml(Mail mail);

    void sendMailWithAttachments(Mail mail, MultipartFile multipartFile);
}

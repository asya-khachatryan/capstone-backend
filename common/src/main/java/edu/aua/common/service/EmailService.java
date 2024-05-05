package edu.aua.common.service;

import edu.aua.common.model.EmailDTO;
import org.springframework.web.multipart.MultipartFile;

public interface EmailService {

    void sendEmail(EmailDTO emailDTO);

    void sendEmailHtml(EmailDTO emailDTO);

    void sendMailWithAttachments(EmailDTO emailDTO, MultipartFile multipartFile);
}

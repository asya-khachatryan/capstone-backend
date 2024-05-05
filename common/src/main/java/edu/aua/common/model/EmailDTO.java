package edu.aua.common.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URI;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailDTO {

    @NotBlank(message = "Email is required")
    @Email(message = "Email is not valid")
    private String emailTo;

    @NotBlank(message = "subject is required")
    private String subject;

    private String text;

    private URI linkOfCalendar;

    public EmailDTO(String emailTo, String subject, String text) {
        this.emailTo = emailTo;
        this.subject = subject;
        this.text = text;
    }
}

package edu.aua.interviews.persistance.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class InterviewerDTO {

    private Long id;

    @NotBlank(message = "required")
    private String firstName;

    @NotBlank(message = "required")
    private String lastName;

    @Email
    private String email;
}

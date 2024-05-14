package edu.aua.onboardingservice.persistance;

import edu.aua.common.model.SpecializationDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MenteeDto {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private Boolean onboardingDocumentSent;

    private SpecializationDTO specialization;
}

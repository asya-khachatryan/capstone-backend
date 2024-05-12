package edu.aua.onboardingservice.service.dto;

import edu.aua.talents.persistance.dto.SpecializationResponseDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MenteeDto extends UserDto {

    private Long mentorId;

    private Long roadmapId;

    private Boolean onboardingDocumentSent;

    private SpecializationResponseDTO specialization;
}

package edu.aua.talents.service.dto;

import edu.aua.talents.persistance.enums.TalentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TalentResponseDTO {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private SpecializationResponseDTO specialization;
    private TalentStatus status;
    private String cvFileName;
    private LocalDateTime dateApplied;
}

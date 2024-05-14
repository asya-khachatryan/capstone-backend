package edu.aua.talents.persistance.dto;

import edu.aua.common.model.SpecializationDTO;
import edu.aua.talents.persistance.TalentStatus;
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
    private SpecializationDTO specialization;
    private TalentStatus status;
    private String cvFileName;
    private LocalDateTime dateApplied;
}

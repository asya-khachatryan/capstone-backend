package edu.aua.talents.service.dto;

import edu.aua.talents.persistance.enums.TalentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TalentRequestDTO {
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private Long specializationId;
    private TalentStatus status;
}

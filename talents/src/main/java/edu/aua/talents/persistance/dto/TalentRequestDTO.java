package edu.aua.talents.persistance.dto;

import edu.aua.talents.persistance.TalentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

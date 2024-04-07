package edu.aua.talents.service.dto;

import edu.aua.talents.service.enums.TalentStatusClientType;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty(value = "status")
    private TalentStatusClientType talentStatusClientType;
}

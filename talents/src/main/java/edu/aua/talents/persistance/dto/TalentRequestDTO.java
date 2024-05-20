package edu.aua.talents.persistance.dto;

import edu.aua.talents.persistance.TalentStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TalentRequestDTO {
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "Surname is mandatory")
    private String surname;
    @Email
    private String email;
    @NotBlank(message = "Name is mandatory")
    private String phoneNumber;
    @NotNull(message = "Specialization is mandatory")
    private Long specializationId;
    private TalentStatus status;
}

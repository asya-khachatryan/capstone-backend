package edu.aua.onboardingservice.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String displayName;

    @JsonProperty(value = "accountId")
    private String accountId;
}

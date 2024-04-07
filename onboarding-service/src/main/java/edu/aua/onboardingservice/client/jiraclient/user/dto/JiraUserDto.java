package edu.aua.onboardingservice.client.jiraclient.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JiraUserDto {
    private String self;
    private String id;
    private String key;
    private String accountId;
    private String emailAddress;
    private String displayName;

    public JiraUserDto(final String emailAddress, final String displayName) {
        this.emailAddress = emailAddress;
        this.displayName = displayName;
    }
}

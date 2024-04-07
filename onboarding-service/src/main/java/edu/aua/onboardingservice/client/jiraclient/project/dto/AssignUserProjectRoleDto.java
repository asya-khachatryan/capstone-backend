package edu.aua.onboardingservice.client.jiraclient.project.dto;

import edu.aua.onboardingservice.client.jiraclient.enums.RoleType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AssignUserProjectRoleDto {

    private List<String> user;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private RoleType role;

    // todo check why data annotation doesn't work
    public AssignUserProjectRoleDto(List<String> user, RoleType role) {
        this.user = user;
        this.role = role;
    }
}

package edu.aua.onboardingservice.client.jiraclient.project.dto;

import edu.aua.onboardingservice.client.jiraclient.enums.RoleType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
public class ProjectRoleDto {

    @JsonProperty(value = "Administrator")
    private String admin;

    @JsonProperty(value = "Viewer")
    private String viewer;

    @JsonProperty(value = "Member")
    private String member;

    // TODO
    public String getProjectRoleUrlFrom(final RoleType role) {
        switch (role) {
            case MEMBER:
                return getMember();
            case VIEWER:
                return getViewer();
        }
        return getAdmin();
    }

}

package edu.aua.onboardingservice.client.jiraclient.project.dto;

import edu.aua.onboardingservice.client.jiraclient.enums.AssigneeType;
import edu.aua.onboardingservice.client.jiraclient.user.dto.JiraUserDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectResponseDto {

    private String self;
    private Long id;
    @JsonProperty(value = "key")
    private String key;
    private String name;
    private String description;
    private JiraUserDto lead;
    private String projectTemplateKey;
    private String uuid;
    private AssigneeType assigneeType;
}

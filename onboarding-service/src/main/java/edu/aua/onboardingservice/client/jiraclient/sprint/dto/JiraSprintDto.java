package edu.aua.onboardingservice.client.jiraclient.sprint.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class JiraSprintDto {

    private Long id;
    private String self;
    private String state;
    private String name;
    private String endDate;
    private String startDate;
    private Long originBoardId;
    private String projectKey;

}

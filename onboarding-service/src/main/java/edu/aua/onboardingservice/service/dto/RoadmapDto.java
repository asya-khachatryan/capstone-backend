package edu.aua.onboardingservice.service.dto;

import edu.aua.onboardingservice.client.jiraclient.sprint.dto.JiraSprintDto;
import edu.aua.onboardingservice.service.enums.RoadmapStatusClientType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoadmapDto {

    private Long id;

    private Date startDate;

    private Date endDate;

    private Long mentorId;

    private Long menteeId;

    private String name;

    private String description;

    private RoadmapStatusClientType status;

    private String jiraProjectKey;

    private List<JiraSprintDto> jiraSprintDtos;
}

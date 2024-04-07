package edu.aua.onboardingservice.converter;

import edu.aua.onboardingservice.client.jiraclient.sprint.dto.JiraSprintDto;
import edu.aua.onboardingservice.persistance.entity.Sprint;
import edu.aua.onboardingservice.service.dto.SprintDto;

import java.util.List;


public interface SprintConverter {

    List<JiraSprintDto> bulkConvertToDTO(List<Sprint> sprints);

    SprintDto convertToDto(Sprint sprint);

    JiraSprintDto convertToJiraDto(SprintDto sprintDto);
}

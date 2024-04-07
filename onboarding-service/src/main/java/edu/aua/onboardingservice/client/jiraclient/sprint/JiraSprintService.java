package edu.aua.onboardingservice.client.jiraclient.sprint;

import edu.aua.onboardingservice.client.jiraclient.sprint.dto.JiraSprintDto;

public interface JiraSprintService {

    JiraSprintDto create(JiraSprintDto sprint);

    void delete(Long id);

}

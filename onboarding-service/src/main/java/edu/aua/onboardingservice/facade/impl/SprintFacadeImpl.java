package edu.aua.onboardingservice.facade.impl;

import edu.aua.onboardingservice.annotation.Facade;
import edu.aua.onboardingservice.client.jiraclient.JiraIntegrationClientFacade;
import edu.aua.onboardingservice.client.jiraclient.project.dto.ProjectBoardDto;
import edu.aua.onboardingservice.client.jiraclient.sprint.dto.JiraSprintDto;
import edu.aua.onboardingservice.converter.SprintConverter;
import edu.aua.onboardingservice.facade.SprintFacade;
import edu.aua.onboardingservice.persistance.entity.Sprint;
import edu.aua.onboardingservice.service.RoadmapService;
import edu.aua.onboardingservice.service.SprintService;
import edu.aua.onboardingservice.service.dto.SprintDto;

@Facade
public class SprintFacadeImpl implements SprintFacade {

    private final JiraIntegrationClientFacade jiraClientFacade;
    private final SprintService sprintService;
    private final SprintConverter sprintConverter;
    private final JiraIntegrationClientFacade jiraIntegrationClientFacade;
    private final RoadmapService roadmapService;

    public SprintFacadeImpl(final JiraIntegrationClientFacade jiraClientFacade,
                            final SprintService sprintService,
                            final SprintConverter sprintConverter,
                            final JiraIntegrationClientFacade jiraIntegrationClientFacade,
                            final RoadmapService roadmapService) {
        this.jiraClientFacade = jiraClientFacade;
        this.sprintService = sprintService;
        this.sprintConverter = sprintConverter;
        this.jiraIntegrationClientFacade = jiraIntegrationClientFacade;
        this.roadmapService = roadmapService;
    }

    @Override
    public SprintDto create(final SprintDto sprintDto) {
        final Sprint sprint = sprintService.create(sprintDto);
        final SprintDto responseDto = this.sprintConverter.convertToDto(sprint);
        final JiraSprintDto jiraSprintDto = this.sprintConverter.convertToJiraDto(sprintDto);
        final ProjectBoardDto projectBoards = jiraIntegrationClientFacade
                .getProjectBoards(sprint.getRoadmap().getJiraProjectKey(), sprint.getRoadmap().getName());
        final Long boardId = projectBoards.getValues().getFirst().getId();
        jiraSprintDto.setId(boardId);
        jiraClientFacade.createSprint(jiraSprintDto);
        return responseDto;
    }

    @Override
    public void delete(final Long id) {

    }
}

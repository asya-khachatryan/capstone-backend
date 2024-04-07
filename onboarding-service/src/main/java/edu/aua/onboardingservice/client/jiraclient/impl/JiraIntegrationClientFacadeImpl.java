package edu.aua.onboardingservice.client.jiraclient.impl;

import edu.aua.onboardingservice.annotation.Facade;
import edu.aua.onboardingservice.client.jiraclient.JiraIntegrationClientFacade;
import edu.aua.onboardingservice.client.jiraclient.project.JiraProjectService;
import edu.aua.onboardingservice.client.jiraclient.project.dto.AssignUserProjectRoleDto;
import edu.aua.onboardingservice.client.jiraclient.project.dto.ProjectBoardDto;
import edu.aua.onboardingservice.client.jiraclient.project.dto.ProjectRequestDto;
import edu.aua.onboardingservice.client.jiraclient.project.dto.ProjectResponseDto;
import edu.aua.onboardingservice.client.jiraclient.project.dto.ProjectRoleDto;
import edu.aua.onboardingservice.client.jiraclient.project.dto.ProjectRoleResponseDto;
import edu.aua.onboardingservice.client.jiraclient.sprint.JiraSprintService;
import edu.aua.onboardingservice.client.jiraclient.sprint.dto.JiraSprintDto;
import edu.aua.onboardingservice.client.jiraclient.user.JiraUserService;
import edu.aua.onboardingservice.client.jiraclient.user.dto.JiraUserDto;
import lombok.extern.slf4j.Slf4j;

@Facade
@Slf4j
public class JiraIntegrationClientFacadeImpl implements JiraIntegrationClientFacade {

    private final JiraProjectService jiraProjectService;
    private final JiraUserService jiraUserService;
    private final JiraSprintService jiraSprintService;

    public JiraIntegrationClientFacadeImpl(final JiraProjectService jiraProjectService,
                                           final JiraUserService jiraUserService,
                                           final JiraSprintService jiraSprintService) {
        this.jiraProjectService = jiraProjectService;
        this.jiraUserService = jiraUserService;
        this.jiraSprintService = jiraSprintService;
    }

    @Override
    public ProjectResponseDto createProject(final ProjectRequestDto project) {
        log.info("In project create method");
        return this.jiraProjectService.createProject(project);
    }

    @Override
    public ProjectResponseDto getProject(final String projectKey) {
        return this.jiraProjectService.getProject(projectKey);
    }

    @Override
    public ProjectRoleDto getProjectRoles(final String projectKey) {
        return this.jiraProjectService.getProjectRoles(projectKey);
    }

    @Override
    public JiraUserDto createUser(final JiraUserDto user) {
        return jiraUserService.createUser(user);
    }

    @Override
    public void deleteUser(final String accountId) {
        this.jiraUserService.deleteUser(accountId);
    }

    @Override
    public ProjectRoleResponseDto addUserToProject(final String projectKey, final AssignUserProjectRoleDto user) {
        final ProjectRoleDto projectRoles = this.jiraProjectService.getProjectRoles(projectKey);
        final String url = projectRoles.getProjectRoleUrlFrom(user.getRole());
        return this.jiraProjectService.addUserToProject(url, user);
    }

    @Override
    public ProjectBoardDto getProjectBoards(final String projectId, final String projectName) {
        return this.jiraProjectService.getProjectBoard(projectId, projectName);
    }

    @Override
    public JiraSprintDto createSprint(final JiraSprintDto jiraSprintDto) {
        return this.jiraSprintService.create(jiraSprintDto);
    }

    @Override
    public void deleteSprint(final Long id) {
        this.jiraSprintService.delete(id);
    }
}

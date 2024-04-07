package edu.aua.onboardingservice.client.jiraclient;

import edu.aua.onboardingservice.client.jiraclient.project.dto.*;
import edu.aua.onboardingservice.client.jiraclient.sprint.dto.JiraSprintDto;
import edu.aua.onboardingservice.client.jiraclient.user.dto.JiraUserDto;

public interface JiraIntegrationClientFacade {

    ProjectResponseDto createProject(ProjectRequestDto project);

    ProjectResponseDto getProject(String projectKey);

    ProjectRoleDto getProjectRoles(String projectKey);

    JiraUserDto createUser(JiraUserDto user);

    void deleteUser(String accountId);

    ProjectRoleResponseDto addUserToProject(String projectKey, AssignUserProjectRoleDto user);

    ProjectBoardDto getProjectBoards(String projectId, String projectName);

    JiraSprintDto createSprint(JiraSprintDto jiraSprintDto);

    void deleteSprint(Long id);

}

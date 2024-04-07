package edu.aua.onboardingservice.client.jiraclient.project;

import edu.aua.onboardingservice.client.jiraclient.project.dto.*;

public interface JiraProjectService {

    ProjectResponseDto createProject(ProjectRequestDto project);

    ProjectResponseDto getProject(String projectKey);

    ProjectRoleDto getProjectRoles(String projectKey);

    ProjectRoleResponseDto addUserToProject(String url,AssignUserProjectRoleDto user);

    ProjectBoardDto getProjectBoard(String projectId, String projectName);

    //ProjectDto updateProject()
    //delete Project
}

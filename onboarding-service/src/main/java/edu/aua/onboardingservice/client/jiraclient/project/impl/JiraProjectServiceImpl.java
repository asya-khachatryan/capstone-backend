package edu.aua.onboardingservice.client.jiraclient.project.impl;

import edu.aua.onboardingservice.client.jiraclient.project.JiraProjectService;
import edu.aua.onboardingservice.client.jiraclient.project.dto.*;
import edu.aua.onboardingservice.config.JiraIntegrationProperties;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@Log4j2
public class JiraProjectServiceImpl implements JiraProjectService {

    private final RestTemplate restTemplate;
    private final JiraIntegrationProperties properties;

    public JiraProjectServiceImpl(final RestTemplate restTemplate,
                                  final JiraIntegrationProperties properties) {
        this.restTemplate = restTemplate;
        this.properties = properties;
    }

    @Override
    public ProjectResponseDto createProject(final ProjectRequestDto project) {
        log.info("Started project create method");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(properties.getUsername(), properties.getMyAccessToken());
        final HttpEntity<ProjectRequestDto> httpEntity = new HttpEntity<>(project, headers);
        final String finalUrl = properties.getUri() + "/project";
        final ProjectResponseDto response = restTemplate
                .exchange(finalUrl, HttpMethod.POST, httpEntity, ProjectResponseDto.class)
                .getBody();
        log.info("Finished project create method");
        return response;
    }

    @Override
    public ProjectResponseDto getProject(final String projectKey) {
        log.info("Started getProject method");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(properties.getUsername(), properties.getMyAccessToken());
        final HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        final String finalUrl = properties.getProjectUri() + "/" + projectKey;
        final ProjectResponseDto responseDto = restTemplate
                .exchange(finalUrl, HttpMethod.GET, httpEntity, ProjectResponseDto.class)
                .getBody();
        log.info("Finished getProject method");

        return responseDto;
    }

    @Override
    public ProjectRoleResponseDto addUserToProject(final String url, final AssignUserProjectRoleDto user) {
        log.info("Started addUserToProject method");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(properties.getUsername(), properties.getMyAccessToken());
        final HttpEntity<?> httpEntity = new HttpEntity<>(user, headers);
        final ProjectRoleResponseDto response = restTemplate
                .exchange(url, HttpMethod.POST, httpEntity, ProjectRoleResponseDto.class)
                .getBody();
        log.info("Finished addUserToProject method");

        return response;
    }

    @Override
    public ProjectRoleDto getProjectRoles(final String projectKey) {
        log.info("Started getProjectRoles method");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(properties.getUsername(), properties.getMyAccessToken());
        final HttpEntity<ProjectRoleDto> httpEntity = new HttpEntity<>(headers);
        final String finalUrl = properties.getUri() + "/project/" + projectKey + "/role";
        final ProjectRoleDto response = restTemplate
                .exchange(finalUrl, HttpMethod.GET, httpEntity, ProjectRoleDto.class)
                .getBody();
        log.info("Finished getProjectRoles method");

        return response;
    }

    @Override
    public ProjectBoardDto getProjectBoard(final String projectId, final String projectName) {
        log.info("Started getProjectBoard method");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(properties.getUsername(), properties.getMyAccessToken());
        final HttpEntity<ProjectBoardDto> httpEntity = new HttpEntity<>(headers);
        final String finalUrl = properties.getProjectBoardUri();
        final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(finalUrl)
                .queryParam("projectId", projectId)
                .queryParam("name", projectName);
        final ProjectBoardDto response = restTemplate
                .exchange(builder.toUriString(), HttpMethod.GET, httpEntity, ProjectBoardDto.class)
                .getBody();
        log.info("Finished getProjectBoard method");

        return response;
    }
}

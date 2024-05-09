package edu.aua.onboardingservice.client.jiraclient.sprint.impl;

import edu.aua.onboardingservice.client.jiraclient.sprint.JiraSprintService;
import edu.aua.onboardingservice.client.jiraclient.sprint.dto.JiraSprintDto;
import edu.aua.onboardingservice.config.JiraIntegrationProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class JiraSprintServiceImpl implements JiraSprintService {
    private final RestTemplate restTemplate;
    private final JiraIntegrationProperties properties;

    public JiraSprintServiceImpl(final RestTemplate restTemplate,
                                 final JiraIntegrationProperties properties) {
        this.restTemplate = restTemplate;
        this.properties = properties;
    }

    @Override
    public JiraSprintDto create(final JiraSprintDto sprint) {
        log.info("Started createSprint method");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(properties.getUsername(), properties.getMyAccessToken());
        final HttpEntity<JiraSprintDto> httpEntity = new HttpEntity<>(sprint, headers);
        final String finalUrl = properties.getSprintUri();
        final JiraSprintDto response = restTemplate.exchange(finalUrl, HttpMethod.POST, httpEntity, JiraSprintDto.class).getBody();
        log.info("Finished createSprint method");
        return response;
    }

    @Override
    public void delete(final Long id) {
        log.info("Started delete method");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(properties.getUsername(), properties.getMyAccessToken());
        final HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        final String finalUrl = properties.getSprintUri() + "/" + id;
        restTemplate.exchange(finalUrl, HttpMethod.DELETE, httpEntity, Void.class);

        log.info("Finished delete method");
    }
}

package edu.aua.onboardingservice.client.jiraclient.user.impl;

import edu.aua.onboardingservice.client.jiraclient.user.JiraUserService;
import edu.aua.onboardingservice.client.jiraclient.user.dto.JiraUserDto;
import edu.aua.onboardingservice.config.JiraIntegrationProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@Slf4j
public class JiraUserServiceImpl implements JiraUserService {
    private final RestTemplate restTemplate;
    private final JiraIntegrationProperties properties;

    public JiraUserServiceImpl(final RestTemplate restTemplate,
                               final JiraIntegrationProperties properties) {
        this.restTemplate = restTemplate;
        this.properties = properties;
    }


    @Override
    public JiraUserDto createUser(final JiraUserDto user) {
        log.info("Started createUser method");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(properties.getUsername(), properties.getMyAccessToken());
        final HttpEntity<JiraUserDto> httpEntity = new HttpEntity<>(user, headers);
        final String finalUrl = properties.getUri() + "/user/";
        log.info("Finished createUser method");
        return restTemplate.postForObject(finalUrl, httpEntity, JiraUserDto.class);
    }

    @Override
    public void deleteUser(final String accountId) {
        log.info("Started deleteUser method");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(properties.getUsername(), properties.getMyAccessToken());
        final HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        final String finalUrl = properties.getUri() + "/user/";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(finalUrl)
                .queryParam("accountId", accountId);
        restTemplate.exchange(builder.toUriString(), HttpMethod.DELETE, httpEntity, Void.class);
        log.info("Finished deleteUser method");
    }
}

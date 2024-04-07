package edu.aua.onboardingservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:/jira.properties")
@ConfigurationProperties(prefix = "jira")
@Getter
@Setter
public class JiraIntegrationProperties {

    private String uri;
    private String myAccessToken;
    private String username;
    private String sprintUri;
    private String projectUri;
    private String projectBoardUri;
    private String projectTemplateKey;
}

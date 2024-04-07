package edu.aua.onboardingservice.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Getter
@Component
@PropertySource("classpath:/mail.properties")
@ConfigurationProperties(prefix = "mail")
public class MailProperties {

    private String baseUrl;
    private String path;
}

package edu.aua.calendly.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:/interview.properties")
@ConfigurationProperties(prefix = "interview")
@Getter
@Setter
public class InterviewProperties {

    private String url;
    private String path;
}

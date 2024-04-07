package edu.aua.calendly.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:/calendly.properties")
@ConfigurationProperties(prefix = "calendly")
@Getter
@Setter
public class CalendlyProperties {

    private String myAccessToken;
    private String url;
    private String scheduledEvent;
}
package edu.aua.calendly.client.impl;

import edu.aua.calendly.client.InterviewClient;
import edu.aua.calendly.client.InterviewEventDTO;
import edu.aua.calendly.config.InterviewProperties;
import edu.aua.calendly.dto.EventDto;
import edu.aua.calendly.dto.WebhookDto;
import edu.aua.interviews.persistance.EventType;
import edu.aua.interviews.persistance.dto.CalendlyEventDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class InterviewClientImpl implements InterviewClient {

    private final RestTemplate restTemplate;
    private final InterviewProperties properties;

    public InterviewClientImpl(final RestTemplate restTemplate,
                               final InterviewProperties properties) {
        this.restTemplate = restTemplate;
        this.properties = properties;
    }
//
//    public void postInterviewEventDTO(final InterviewEventDTO interviewEventDTO) {
//        final HttpEntity<InterviewEventDTO> httpEntity = new HttpEntity<>(interviewEventDTO);
//        final String finalUrl = properties.getUrl() + properties.getPath();
//        restTemplate.exchange(finalUrl, HttpMethod.PUT, httpEntity, Void.class);
//    }

    public CalendlyEventDTO generateInterviewEvent(final WebhookDto webhook, final EventDto event) {
        return CalendlyEventDTO.builder()
                .talentEmail(webhook.getPayload().getParticipantEmail())
                .eventType(parseFrom(webhook.getEvent()))
                .endDate(event.getEndTime())
                .startDate(event.getStartTime())
                .build();
    }

    private EventType parseFrom(final String eventType) {
        return EventType.valueOf(eventType.replace("invitee.","").toUpperCase());
    }
}


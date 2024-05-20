package edu.aua.calendly.service;

import edu.aua.calendly.client.InterviewClient;
import edu.aua.calendly.client.InterviewEventDTO;
import edu.aua.calendly.config.CalendlyProperties;
import edu.aua.calendly.service.CalendarService;
import edu.aua.calendly.dto.EventResponse;
import edu.aua.calendly.dto.WebhookDto;
import edu.aua.interviews.persistance.dto.CalendlyEventDTO;
import edu.aua.interviews.service.InterviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class CalendarServiceImpl implements CalendarService {
    private final InterviewClient interviewClient;
    private final CalendlyProperties calendlyProperties;
    private final InterviewService interviewService;

    @Override
    public void sendEventToClient(final WebhookDto webhook) {
        log.info("Start sendEventToClient");
        final String eventUri = webhook.getPayload().getEventUri();
        final EventResponse event = getEvent(eventUri);
        final CalendlyEventDTO calendlyEventDTO = interviewClient.generateInterviewEvent(webhook, event.getResource());
//        interviewClient.postInterviewEventDTO(interviewEvent);
        interviewService.addEvent(calendlyEventDTO);
        log.info("Finished sendEventToClient");
    }

    public EventResponse getEvent(final String uri) {
        log.info("Start getEvent");
        final RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + calendlyProperties.getMyAccessToken());
        HttpEntity<EventResponse> httpEntity = new HttpEntity<>(headers);
        log.info("Finished getEvent");
        return restTemplate.exchange(uri, HttpMethod.GET, httpEntity, EventResponse.class).getBody();
    }

    public <T> void post(String uri, T data) {
        log.info("Start post");
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<T> httpEntity = new HttpEntity<>(data);
        restTemplate.exchange(uri, HttpMethod.POST, httpEntity, Void.class);
        log.info("Finished post");
    }
}
package edu.aua.calendly.client;

import edu.aua.calendly.service.dto.EventDto;
import edu.aua.calendly.service.dto.WebhookDto;

public interface InterviewClient {

    InterviewEventDTO generateInterviewEvent(WebhookDto webhook, EventDto event);

    void postInterviewEventDTO(InterviewEventDTO interviewEventDTO);
}

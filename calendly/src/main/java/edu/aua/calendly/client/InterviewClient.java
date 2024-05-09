package edu.aua.calendly.client;

import edu.aua.calendly.dto.EventDto;
import edu.aua.calendly.dto.WebhookDto;

public interface InterviewClient {

    InterviewEventDTO generateInterviewEvent(WebhookDto webhook, EventDto event);

    void postInterviewEventDTO(InterviewEventDTO interviewEventDTO);
}

package edu.aua.calendly.client;

import edu.aua.calendly.dto.EventDto;
import edu.aua.calendly.dto.WebhookDto;
import edu.aua.interviews.persistance.dto.CalendlyEventDTO;

public interface InterviewClient {

    CalendlyEventDTO generateInterviewEvent(WebhookDto webhook, EventDto event);

//    void postInterviewEventDTO(InterviewEventDTO interviewEventDTO);
}

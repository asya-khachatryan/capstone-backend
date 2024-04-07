package edu.aua.calendly.service;

import edu.aua.calendly.service.dto.EventResponse;
import edu.aua.calendly.service.dto.WebhookDto;

public interface CalendarService {

    void sendEventToClient(WebhookDto webhook);

    <T> void post(String uri, T data);

    EventResponse getEvent(final String uri);

}

package edu.aua.calendly.service;

import edu.aua.calendly.dto.EventResponse;
import edu.aua.calendly.dto.WebhookDto;

public interface CalendarService {

    void sendEventToClient(WebhookDto webhook);

    <T> void post(String uri, T data);

    EventResponse getEvent(final String uri);

}

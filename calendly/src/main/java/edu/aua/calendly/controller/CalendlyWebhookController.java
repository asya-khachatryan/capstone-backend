package edu.aua.calendly.controller;

import edu.aua.calendly.service.CalendarService;
import edu.aua.calendly.service.dto.WebhookDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CalendlyWebhookController {

    private final CalendarService calendarService;

    public CalendlyWebhookController(final CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @PostMapping("/getPayload")
    public void getMyPayload(@RequestBody WebhookDto webhook) {
        this.calendarService.sendEventToClient(webhook);
    }
}

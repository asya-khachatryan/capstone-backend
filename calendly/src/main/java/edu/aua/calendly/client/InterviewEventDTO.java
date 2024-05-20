package edu.aua.calendly.client;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Builder
@Data
public class InterviewEventDTO implements Serializable {

    private EventType eventType;

    private LocalDateTime endDate;

    private LocalDateTime startDate;

    private String eventName;

    private String talentEmail;

    private String participantName;
}
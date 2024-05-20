package edu.aua.interviews.persistance.dto;

import edu.aua.interviews.persistance.EventType;
import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Builder
public class CalendlyEventDTO {
    @Email
    private String talentEmail;

    @DateTimeFormat
    private LocalDateTime startDate;

    @DateTimeFormat
    private LocalDateTime endDate;

    private EventType eventType;
}

package edu.aua.talents.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InterviewEventDTO implements Serializable {

    @JsonProperty(value = "event")
    @JsonIgnore
    private String eventType;

    @JsonProperty(value = "end_time")
    private LocalDate endTime;

    @JsonProperty(value = "start_time")
    private LocalDate startTime;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty("talent_email")
    private String talentEmail;
}

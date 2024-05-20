package edu.aua.calendly.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
public class EventDto {

    @JsonProperty(value = "name")
    private String eventName;

    @JsonProperty(value = "start_time")
    private LocalDateTime startTime;

    @JsonProperty(value = "end_time")
    private LocalDateTime endTime;

}

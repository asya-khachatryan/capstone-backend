package edu.aua.calendly.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
public class EventDto {

    @JsonProperty(value = "name")
    private String eventName;

    @JsonProperty(value = "start_time")
    private Date startTime;

    @JsonProperty(value = "end_time")
    private Date endTime;

}

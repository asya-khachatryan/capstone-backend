package edu.aua.calendly.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
public class PayloadDto {

    @JsonProperty(value = "email")
    private String participantEmail;

    @JsonProperty(value = "name")
    private String participantName;

    @JsonProperty(value = "event")
    private String eventUri;

    @JsonProperty(value = "canceled")
    private Boolean canceled;

}

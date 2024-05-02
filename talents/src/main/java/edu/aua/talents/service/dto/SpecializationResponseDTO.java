package edu.aua.talents.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SpecializationResponseDTO {

    private Long id;
    @JsonProperty(value = "specialization")
    private String specializationClientType;
}

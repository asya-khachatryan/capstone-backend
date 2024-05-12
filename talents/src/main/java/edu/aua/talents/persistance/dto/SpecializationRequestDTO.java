package edu.aua.talents.persistance.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SpecializationRequestDTO {
    @JsonProperty(value = "specialization")
    private String specializationClientType;

    public String getSpecializationClientType() {
        return specializationClientType;
    }

    public void setSpecializationClientType(String specializationClientType) {
        this.specializationClientType = specializationClientType;
    }
}

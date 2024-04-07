package edu.aua.interviews.service.dto;


import javax.validation.constraints.NotEmpty;

public class SpecializationDTO {

    private Long id;

    @NotEmpty
    private String specializationType;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpecializationType() {
        return specializationType;
    }

    public void setSpecializationType(String specializationType) {
        this.specializationType = specializationType;
    }
}

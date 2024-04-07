package edu.aua.talents.service.dto;

import edu.aua.talents.service.enums.TalentStatusClientType;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TalentResponseDTO {

    private Long id;

    private String name;

    private String surname;

    private String email;

    private String phoneNumber;

    private SpecializationResponseDTO specialization;

    @JsonProperty(value = "status")
    private TalentStatusClientType talentStatusClientType;

    private String cvFileName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public SpecializationResponseDTO getSpecialization() {
        return specialization;
    }

    public void setSpecialization(SpecializationResponseDTO specialization) {
        this.specialization = specialization;
    }

    public TalentStatusClientType getTalentStatusClientType() {
        return talentStatusClientType;
    }

    public void setTalentStatusClientType(TalentStatusClientType talentStatusClientType) {
        this.talentStatusClientType = talentStatusClientType;
    }

    public String getCvFileName() {
        return cvFileName;
    }

    public void setCvFileName(String cvFileName) {
        this.cvFileName = cvFileName;
    }
}

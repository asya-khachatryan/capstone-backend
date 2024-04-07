package edu.aua.interviews.service.dto;

import edu.aua.interviews.persistance.enums.TalentStatus;

import javax.validation.constraints.*;

public class TalentDTO {

    private Long id;

    @NotBlank(message = "name must not be blank")
    private String name;

    @NotBlank(message = "surname must not be blank")
    private String surname;

    @Email
    private String email;

    @Pattern(regexp="\\d{9}")
    private String phoneNumber;

    @Positive
    @Min(value = 50)
    @Max(value = 100)
    private Integer overallScore;

    @NotEmpty
    private TalentStatus talentStatus;

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

    public Integer getOverallScore() {
        return overallScore;
    }

    public void setOverallScore(Integer overallScore) {
        this.overallScore = overallScore;
    }

    public TalentStatus getTalentStatus() {
        return talentStatus;
    }

    public void setTalentStatus(TalentStatus talentStatus) {
        this.talentStatus = talentStatus;
    }
}

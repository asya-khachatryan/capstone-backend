package edu.aua.interviews.service.dto;


import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class InterviewRequestDTO {

    @NotBlank
    @URL
    private String calendarURI;

    @NotNull
    
    private List<Long> userIDs;

    @NotNull
    private TalentDTO talentDTO;

    @NotNull
    private Long specializationId;

    public String getCalendarURI() {
        return calendarURI;
    }

    public void setCalendarURI(String calendarURI) {
        this.calendarURI = calendarURI;
    }

    public TalentDTO getTalentDTO() {
        return talentDTO;
    }

    public void setTalentDTO(TalentDTO talentDTO) {
        this.talentDTO = talentDTO;
    }

    public List<Long> getUserIDs() {
        return userIDs;
    }

    public void setUserIDs(List<Long> userIDs) {
        this.userIDs = userIDs;
    }

    public Long getSpecializationId() {
        return specializationId;
    }


    public void setSpecializationId(Long specializationId) {
        this.specializationId = specializationId;
    }
}

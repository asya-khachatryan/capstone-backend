package edu.aua.interviews.service.dto;

import edu.aua.interviews.persistance.enums.EventType;
import org.hibernate.annotations.Entity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import java.util.Date;

public class InterviewEventDTO {


    @Email
    private String talentEmail;

    @DateTimeFormat
    private Date startDate;

    @DateTimeFormat
    private Date endDate;


    private EventType eventType;

    public String getTalentEmail() {
        return talentEmail;
    }

    public void setTalentEmail(String talentEmail) {
        this.talentEmail = talentEmail;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }
}

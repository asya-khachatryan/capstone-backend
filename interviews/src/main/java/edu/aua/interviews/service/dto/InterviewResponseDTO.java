package edu.aua.interviews.service.dto;

import edu.aua.interviews.persistance.enums.InterviewStatus;
import edu.aua.interviews.persistance.enums.InterviewType;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class InterviewResponseDTO {

    private Long id;

    @DateTimeFormat
    private Date date;//ste anel start u end

    @NotEmpty
    private InterviewType interviewType;

    @NotEmpty
    private InterviewStatus interviewStatus;

    @NotNull
    private TalentDTO talentDTO;

    @NotNull
    private List<UserDTO> userDTO;

    @NotNull
    private InterviewFeedbackResponseDTO interviewFeedback;

    @NotNull
    private SpecializationDTO specialization;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public InterviewType getInterviewType() {
        return interviewType;
    }

    public void setInterviewType(InterviewType interviewType) {
        this.interviewType = interviewType;
    }

    public InterviewStatus getInterviewStatus() {
        return interviewStatus;
    }

    public void setInterviewStatus(InterviewStatus interviewStatus) {
        this.interviewStatus = interviewStatus;
    }

    public TalentDTO getTalentDTO() {
        return talentDTO;
    }

    public void setTalentDTO(TalentDTO talentDTO) {
        this.talentDTO = talentDTO;
    }

    public List<UserDTO> getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(List<UserDTO> userDTO) {
        this.userDTO = userDTO;
    }

    public InterviewFeedbackResponseDTO getInterviewFeedback() {
        return interviewFeedback;
    }

    public void setInterviewFeedback(InterviewFeedbackResponseDTO interviewFeedback) {
        this.interviewFeedback = interviewFeedback;
    }

    public SpecializationDTO getSpecialization() {
        return specialization;
    }

    public void setSpecialization(SpecializationDTO specialization) {
        this.specialization = specialization;
    }
}

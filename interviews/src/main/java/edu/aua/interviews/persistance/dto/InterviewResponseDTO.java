package edu.aua.interviews.persistance.dto;

import edu.aua.interviews.persistance.InterviewStatus;
import edu.aua.interviews.persistance.InterviewType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
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
    private InterviewFeedbackDTO interviewFeedback;

    @NotNull
    private SpecializationDTO specialization;
}

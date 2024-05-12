package edu.aua.interviews.persistance.dto;

import edu.aua.interviews.persistance.InterviewStatus;
import edu.aua.interviews.persistance.InterviewType;
import edu.aua.talents.persistance.dto.TalentResponseDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class InterviewResponseDTO {

    private Long id;

    //    @DateTimeFormat
    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @NotEmpty
    private InterviewType interviewType;

    @NotEmpty
    private InterviewStatus interviewStatus;

    @NotNull
    private TalentResponseDTO talentDTO;

    @NotNull
    private List<InterviewerDTO> interviewerDTO;

    @NotNull
    private InterviewFeedbackDTO interviewFeedback;
}

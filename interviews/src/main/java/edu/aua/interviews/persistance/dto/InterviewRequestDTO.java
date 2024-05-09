package edu.aua.interviews.persistance.dto;


import edu.aua.interviews.persistance.InterviewType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.util.List;

@Data
public class InterviewRequestDTO {

    @NotBlank
    @URL
    private String calendarURI;

    @NotNull
    private List<Long> userIDs;

    @NotNull
    private Long talentID;

    @NotBlank
    private InterviewType interviewType;
}

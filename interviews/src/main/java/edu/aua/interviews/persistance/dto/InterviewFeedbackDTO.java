package edu.aua.interviews.persistance.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class InterviewFeedbackDTO {

    private Long id;

    @NotBlank(message = "topic must not be blank")
    private String topic;

    @NotBlank(message = "feedback must not be blank")
    private String feedback;

    @PositiveOrZero
    @Min(value = 0)
    @Max(value = 100)
    private Integer score;
}

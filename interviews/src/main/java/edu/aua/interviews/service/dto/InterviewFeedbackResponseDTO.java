package edu.aua.interviews.service.dto;

import javax.validation.constraints.*;

public class InterviewFeedbackResponseDTO {

    private Long id;

    @NotBlank(message = "topic must not be blank")
    private String topic;

    @NotBlank(message = "feedback must not be blank")
    private String feedback;

    @PositiveOrZero
    @Min(value = 0)
    @Max(value = 100)
    private Integer score;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

}

package edu.aua.interviews.persistance;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "interviewFeedback", schema = "interview_flow")
@Data
@EqualsAndHashCode
public class InterviewFeedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "topic")
    private String topic;

    @Column(name = "feedback")
    private String feedback;

    @Column(name = "score")
    private Integer score;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "id")
    @MapsId
    private Interview interview;
}

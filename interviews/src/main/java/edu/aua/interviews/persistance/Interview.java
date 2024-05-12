package edu.aua.interviews.persistance;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import edu.aua.talents.persistance.Talent;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "interview")
@Data
public class Interview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "startDate")
    private LocalDateTime startDate;

    @Column(name = "endDate")
    private LocalDateTime endDate;

    @Transient
    private URI url;

    @Enumerated(EnumType.STRING)
    private InterviewType interviewType;

    @Enumerated(EnumType.STRING)
    private InterviewStatus interviewStatus;

    @OneToOne(mappedBy = "interview", cascade = CascadeType.ALL)
    @JoinColumn(name = "interview_feedback_id")
    private InterviewFeedback interviewFeedback;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "talent_id")
    @JsonBackReference
    private Talent talent;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "interview_interviewers",
            joinColumns = @JoinColumn(name = "interview_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "interviewer_id", referencedColumnName = "id"))
    private List<Interviewer> interviewers = new ArrayList<>();
}


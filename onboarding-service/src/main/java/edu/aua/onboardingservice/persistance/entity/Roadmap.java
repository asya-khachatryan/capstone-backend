package edu.aua.onboardingservice.persistance.entity;

import edu.aua.onboardingservice.persistance.enums.RoadmapStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "roadmap", schema = "onboarding_service_db")
@Data
@NoArgsConstructor
public class Roadmap extends AbstractAuditAwareBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @OneToMany(cascade = CascadeType.ALL,
        orphanRemoval = true, mappedBy = "roadmap")
    private List<Sprint> sprints;

    @ManyToOne
    @JoinColumn(name = "mentor_id")
    private Mentor mentor;

    @OneToOne
    @JoinColumn(name = "mentee_id")
    private Mentee mentee;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    private RoadmapStatus status;

    @Column(name = "jira_project_key")
    private String jiraProjectKey;

    @Column(name = "name")
    private String name;
}

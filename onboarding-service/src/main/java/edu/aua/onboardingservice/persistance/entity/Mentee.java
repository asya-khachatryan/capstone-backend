package edu.aua.onboardingservice.persistance.entity;

import edu.aua.talents.persistance.Specialization;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "mentee", schema = "onboarding_service_db")
@Getter
@Setter
@NoArgsConstructor
public class Mentee extends User {

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "roadmap_id")
    private Roadmap roadmap;

    @ManyToOne
    @JoinColumn(name = "mentor_id")
    private Mentor mentor;

    @Column(name = "onboarding_document_sent", columnDefinition = "boolean default false")
    private Boolean onboardingDocumentSent;

    @ManyToOne
    @JoinColumn(name = "specialization_id")
    private Specialization specialization;
}

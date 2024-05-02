package edu.aua.onboardingservice.persistance.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

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
}

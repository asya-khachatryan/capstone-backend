package edu.aua.onboardingservice.persistance.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "mentor", schema = "onboarding_service_db")
@Getter
@Setter
@NoArgsConstructor
public class Mentor extends User {

    @OneToMany(cascade = CascadeType.ALL,
        orphanRemoval = true, mappedBy = "mentor")
    private List<Roadmap> roadmaps;

    @OneToMany(cascade = CascadeType.ALL,
        orphanRemoval = true, mappedBy = "mentor")
    private List<Mentee> mentees;
}

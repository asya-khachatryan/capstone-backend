package edu.aua.interviews.persistance.entity;


import edu.aua.interviews.persistance.entity.interview.Interview;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "specialization", schema = "interview_flow")
public class Specialization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "specialization_type", unique = true)
    @NotBlank(message = "Specialization type is mandatory")
    private String specializationType;

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true, mappedBy = "specialization")
    private List<Talent> talentList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JoinColumn(name = "specialization_id")
    @JsonIgnore
    private List<Interview> interviews = new ArrayList<>();


    public Specialization() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpecializationType() {
        return specializationType;
    }

    public void setSpecializationType(String specializationType) {
        this.specializationType = specializationType;
    }

    public List<Interview> getInterviews() {
        return interviews;
    }

    public void setInterviews(List<Interview> interviews) {
        this.interviews = interviews;
    }

    public List<Talent> getTalentList() {
        return talentList;
    }

    public void setTalentList(List<Talent> talentList) {
        this.talentList = talentList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Specialization that = (Specialization) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
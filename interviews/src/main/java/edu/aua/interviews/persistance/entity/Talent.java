package edu.aua.interviews.persistance.entity;

import edu.aua.interviews.persistance.entity.interview.Interview;
import edu.aua.interviews.persistance.enums.TalentStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "talent", schema = "interview_flow")
public class Talent {

    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "overallScore")
    private Integer overallScore;

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true, mappedBy = "talent")
    @JsonIgnore
    private List<Interview> interviews = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "specialization_id")
    private Specialization specialization;

    @Enumerated(EnumType.STRING)
    private TalentStatus talentStatus;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public TalentStatus getTalentStatus() {
        return talentStatus;
    }

    public void setTalentStatus(TalentStatus talentStatus) {
        this.talentStatus = talentStatus;
    }

    public Integer getOverallScore() {
        return overallScore;
    }

    public void setOverallScore(Integer overallScore) {
        this.overallScore = overallScore;
    }

    public List<Interview> getInterviews() {
        return interviews;
    }

    public void setInterviews(List<Interview> interviews) {
        this.interviews = interviews;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Talent talent = (Talent) o;

        return id != null ? id.equals(talent.id) : talent.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}



package edu.aua.talents.persistance.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "specialization")
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

    public List<Talent> getTalentList() {
        return talentList;
    }

    public void setTalentList(List<Talent> talentList) {
        this.talentList = talentList;
    }
}

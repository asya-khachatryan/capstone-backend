package edu.aua.talents.repository;

import edu.aua.talents.persistance.Talent;

import java.util.List;

public interface TalentCustomRepository {
    List<Talent> findBySpecializationId(Long specializationId);

}

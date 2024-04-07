package edu.aua.talents.persistance;

import edu.aua.talents.persistance.entity.Talent;

import java.util.List;

public interface TalentCustomRepository {
    List<Talent> findBySpecializationId(Long specializationId);

}

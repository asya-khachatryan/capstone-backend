package edu.aua.talents.persistance;


import edu.aua.talents.persistance.entity.Talent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TalentRepository extends JpaRepository<Talent,Long>, TalentCustomRepository {

    Optional<Talent> findByEmail(String email);

    @Override
    List<Talent> findBySpecializationId(Long specializationId);
}

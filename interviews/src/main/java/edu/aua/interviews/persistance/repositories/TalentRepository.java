package edu.aua.interviews.persistance.repositories;

import edu.aua.interviews.persistance.entity.Talent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TalentRepository extends JpaRepository<Talent, Long> {

    Optional<Talent> findTalentByEmail(String email);

}

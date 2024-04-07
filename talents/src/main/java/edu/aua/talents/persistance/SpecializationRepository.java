package edu.aua.talents.persistance;

import edu.aua.talents.persistance.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization,Long> {
}

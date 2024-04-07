package edu.aua.onboardingservice.persistance;

import edu.aua.onboardingservice.persistance.entity.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, Long> {
}

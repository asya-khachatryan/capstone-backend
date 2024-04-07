package edu.aua.onboardingservice.persistance;

import edu.aua.onboardingservice.persistance.entity.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentorRepository extends JpaRepository<Mentor, Long> {
}

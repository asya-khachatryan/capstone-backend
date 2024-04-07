package edu.aua.onboardingservice.persistance;

import edu.aua.onboardingservice.persistance.entity.Roadmap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoadmapRepository extends JpaRepository<Roadmap, Long> {
    Optional<Roadmap> findByJiraProjectKey(String projectKey);
}

package edu.aua.interviews.repositories;

import edu.aua.interviews.persistance.InterviewFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<InterviewFeedback, Long> {
}

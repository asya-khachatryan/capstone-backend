package edu.aua.interviews.persistance.repositories;

import edu.aua.interviews.persistance.entity.interview.InterviewFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<InterviewFeedback, Long> {

//    List<InterviewFeedback> findAllByTalentIdAndInterview_InterviewStatus_WaitingStage(@Param("talent_id") Long talent_id);

}

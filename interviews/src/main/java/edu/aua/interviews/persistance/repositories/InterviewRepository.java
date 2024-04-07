package edu.aua.interviews.persistance.repositories;

import edu.aua.interviews.persistance.entity.interview.Interview;
import edu.aua.interviews.persistance.enums.InterviewStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Long> {

    List<Interview> findAllByTalent_Id(Long talent_id);

    Interview findAllByTalent_IdAndInterviewStatus(Long talentId, InterviewStatus status);

}

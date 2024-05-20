package edu.aua.interviews.repositories;

import edu.aua.interviews.persistance.Interview;
import edu.aua.interviews.persistance.InterviewStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Long> {

    List<Interview> findAllByTalent_Id(Long talentId);

    @Query("SELECT i FROM Interview i WHERE i.talent.email = :talentEmail")
    List<Interview> findAllByTalentEmail(String talentEmail);
}

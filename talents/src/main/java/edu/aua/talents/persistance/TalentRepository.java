package edu.aua.talents.persistance;


import edu.aua.talents.persistance.entity.Talent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TalentRepository extends JpaRepository<Talent, Long>, TalentCustomRepository {

    Optional<Talent> findByEmail(String email);

    @Override
    List<Talent> findBySpecializationId(Long specializationId);

    @Query("SELECT t FROM Talent t WHERE t.talentStatus = INTERVIEW_PREPARATION" +
            " OR t.talentStatus = STAGE1_INTERVIEW" +
            " OR t.talentStatus = STAGE2_INTERVIEW")
    List<Talent> findInterviewees();

    @Query("SELECT t FROM Talent t WHERE CONCAT(t.name, ' ', t.surname, ' ', t.email, ' ', t.phoneNumber) " +
            "ILIKE %?1%")
    List<Talent> findTalentLike(@Param("query") String query);


}

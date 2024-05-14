package edu.aua.onboardingservice.repository;

import edu.aua.onboardingservice.persistance.Mentee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenteeRepository extends JpaRepository<Mentee, Long> {
    @Query("SELECT m FROM Mentee m WHERE " +
            "    (" +
            "        CONCAT(m.firstName, ' ', m.lastName, ' ', m.email, ' ', m.phoneNumber) ILIKE %?1% " +
            "    )")
    List<Mentee> findMenteesLike(@Param("query") String query);
}

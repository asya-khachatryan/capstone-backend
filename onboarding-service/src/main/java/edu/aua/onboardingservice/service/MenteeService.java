package edu.aua.onboardingservice.service;

import edu.aua.onboardingservice.persistance.Mentee;
import edu.aua.onboardingservice.persistance.MenteeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MenteeService {

    Page<MenteeDto> findAll(Pageable page);

    List<Mentee> findALl();

    Mentee findByIdOrThrow(Long id);

    Mentee create(MenteeDto menteeDTO);

    Mentee update(Long id, MenteeDto menteeDTO);

    boolean deleteById(Long id);

    boolean sendOnboardingEmail(Long menteeId, String hrManagerUsername, String documentUrl);

    List<Mentee> searchMentee(String query);
}

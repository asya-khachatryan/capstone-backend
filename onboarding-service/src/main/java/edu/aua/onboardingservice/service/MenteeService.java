package edu.aua.onboardingservice.service;

import edu.aua.onboardingservice.persistance.Mentee;
import edu.aua.onboardingservice.persistance.MenteeDto;

import java.util.List;

public interface MenteeService {

    List<Mentee> findALl();

    Mentee findById(Long id);

    Mentee create(MenteeDto menteeDTO);

    Mentee update(Long id, MenteeDto menteeDTO);

    boolean deleteById(Long id);

    boolean sendOnboardingEmail(Long menteeId, String hrManagerUsername, String documentUrl);
}

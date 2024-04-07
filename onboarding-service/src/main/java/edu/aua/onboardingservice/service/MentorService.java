package edu.aua.onboardingservice.service;

import edu.aua.onboardingservice.exception.RoadmapNotFoundException;
import edu.aua.onboardingservice.persistance.entity.Mentor;
import edu.aua.onboardingservice.service.dto.MentorDto;

import java.util.List;

public interface MentorService {

    List<Mentor> findALl();

    Mentor findById(Long id);

    Mentor create(MentorDto mentorDTO);

    Mentor update(Long id, MentorDto mentorDTO);

    boolean deleteById(Long id);

}

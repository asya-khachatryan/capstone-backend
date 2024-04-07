package edu.aua.onboardingservice.facade;

import edu.aua.onboardingservice.service.dto.MenteeDto;
import edu.aua.onboardingservice.service.dto.MentorDto;

import java.util.List;

public interface UserFacade {

    MenteeDto createMentee(MenteeDto menteeDto);

    MenteeDto findMenteeById(Long id);

    MentorDto findMentorById(Long id);

    List<MenteeDto> getAllMentees();

    List<MentorDto> getAllMentors();

    boolean deleteMentee(Long id);

    boolean deleteMentor(Long id);

}

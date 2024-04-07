package edu.aua.onboardingservice.converter;

import edu.aua.onboardingservice.persistance.entity.Mentor;
import edu.aua.onboardingservice.service.dto.MentorDto;

import java.util.List;

public interface MentorConverter {

    List<MentorDto> bulkConvertToDTO(List<Mentor> mentors);

    MentorDto convertToDTO(Mentor mentor);

    List<Mentor> bulkConvertToEntity(List<MentorDto> mentorDtos);

    Mentor convertToEntity(MentorDto mentorDTO);

}

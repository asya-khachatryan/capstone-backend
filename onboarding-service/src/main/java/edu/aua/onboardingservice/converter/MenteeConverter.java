package edu.aua.onboardingservice.converter;

import edu.aua.onboardingservice.persistance.Mentee;
import edu.aua.onboardingservice.persistance.MenteeDto;

import java.util.List;

public interface MenteeConverter {

    List<MenteeDto> bulkConvertToDto(List<Mentee> mentees);

    MenteeDto convertToDto(Mentee mentee);

    List<Mentee> bulkConvertToEntity(List<MenteeDto> menteeDtoList);

    Mentee convertToEntity(MenteeDto menteeDto);
}

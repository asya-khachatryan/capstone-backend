package edu.aua.onboardingservice.converter;

import edu.aua.onboardingservice.client.jiraclient.user.dto.JiraUserDto;
import edu.aua.onboardingservice.persistance.entity.Mentee;
import edu.aua.onboardingservice.service.dto.MenteeDto;

import java.util.List;

public interface MenteeConverter {

    List<MenteeDto> bulkConvertToDto(List<Mentee> mentees);

    MenteeDto convertToDto(Mentee mentee);

    JiraUserDto convertToJiraDTO (MenteeDto menteeDTO);

}

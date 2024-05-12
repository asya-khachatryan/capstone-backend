package edu.aua.onboardingservice.converter.impl;

import edu.aua.onboardingservice.annotation.Converter;
import edu.aua.onboardingservice.client.jiraclient.user.dto.JiraUserDto;
import edu.aua.onboardingservice.converter.MenteeConverter;
import edu.aua.onboardingservice.persistance.entity.Mentee;
import edu.aua.onboardingservice.service.dto.MenteeDto;
import edu.aua.talents.converter.SpecializationConverter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Converter
@RequiredArgsConstructor
public class MenteeConverterImpl implements MenteeConverter {

    private final SpecializationConverter specializationConverter;

    @Override
    public List<MenteeDto> bulkConvertToDto(final List<Mentee> mentees) {
        return mentees.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public MenteeDto convertToDto(final Mentee mentee) {
        final MenteeDto menteeDTO = new MenteeDto();
        menteeDTO.setId(mentee.getId());
        menteeDTO.setFirstName(mentee.getFirstName());
        menteeDTO.setLastName(mentee.getLastName());
        menteeDTO.setEmail(mentee.getEmail());
        menteeDTO.setPhoneNumber(mentee.getPhoneNumber());
        menteeDTO.setDisplayName(mentee.getDisplayName());
        menteeDTO.setMentorId(mentee.getMentor().getId());
        menteeDTO.setAccountId(mentee.getAccountId());
        menteeDTO.setOnboardingDocumentSent(mentee.getOnboardingDocumentSent());
        menteeDTO.setSpecialization(specializationConverter.convertToDTO(mentee.getSpecialization()));
        return menteeDTO;
    }

    @Override
    public JiraUserDto convertToJiraDTO(final MenteeDto menteeDTO) {
        final JiraUserDto jiraUserDto = new JiraUserDto();
        jiraUserDto.setDisplayName(menteeDTO.getDisplayName());
        jiraUserDto.setEmailAddress(menteeDTO.getEmail());
        return jiraUserDto;
    }
}

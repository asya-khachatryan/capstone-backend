package edu.aua.onboardingservice.converter.impl;

import edu.aua.common.converter.SpecializationConverter;
import edu.aua.common.service.SpecializationService;
import edu.aua.onboardingservice.annotation.Converter;
import edu.aua.onboardingservice.converter.MenteeConverter;
import edu.aua.onboardingservice.persistance.Mentee;
import edu.aua.onboardingservice.persistance.MenteeDto;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Converter
@RequiredArgsConstructor
public class MenteeConverterImpl implements MenteeConverter {

    private final SpecializationConverter specializationConverter;
    private final SpecializationService specializationService;

    @Override
    public List<MenteeDto> bulkConvertToDto(final List<Mentee> mentees) {
        return mentees.stream().map(this::convertToDto).toList();
    }

    @Override
    public MenteeDto convertToDto(final Mentee mentee) {
        final MenteeDto menteeDTO = new MenteeDto();
        menteeDTO.setId(mentee.getId());
        menteeDTO.setFirstName(mentee.getFirstName());
        menteeDTO.setLastName(mentee.getLastName());
        menteeDTO.setEmail(mentee.getEmail());
        menteeDTO.setPhoneNumber(mentee.getPhoneNumber());
        menteeDTO.setOnboardingDocumentSent(mentee.getOnboardingDocumentSent());
        menteeDTO.setSpecialization(specializationConverter.convertToDTO(mentee.getSpecialization()));
        return menteeDTO;
    }

    @Override
    public List<Mentee> bulkConvertToEntity(List<MenteeDto> menteeDtoList) {
        return menteeDtoList.stream().map(this::convertToEntity).toList();
    }

    @Override
    public Mentee convertToEntity(MenteeDto menteeDto) {
        final Mentee mentee = new Mentee();
        mentee.setFirstName(menteeDto.getFirstName());
        mentee.setLastName(menteeDto.getLastName());
        mentee.setEmail(menteeDto.getEmail());
        mentee.setPhoneNumber(menteeDto.getPhoneNumber());
        mentee.setOnboardingDocumentSent(menteeDto.getOnboardingDocumentSent());
        mentee.setSpecialization(specializationService.findByIdOrThrow(menteeDto.getSpecialization().getId()));
        return mentee;
    }
}

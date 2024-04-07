package edu.aua.onboardingservice.converter.impl;

import edu.aua.onboardingservice.annotation.Converter;
import edu.aua.onboardingservice.converter.MentorConverter;
import edu.aua.onboardingservice.persistance.entity.Mentor;
import edu.aua.onboardingservice.service.dto.MentorDto;

import java.util.List;
import java.util.stream.Collectors;

@Converter
public class MentorConverterImpl implements MentorConverter {

    @Override
    public List<MentorDto> bulkConvertToDTO(List<Mentor> mentors) {
        return mentors.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public MentorDto convertToDTO(Mentor mentor) {
        final MentorDto mentorDTO = new MentorDto();
        mentorDTO.setId(mentor.getId());
        mentorDTO.setFirstName(mentor.getFirstName());
        mentorDTO.setLastName(mentor.getLastName());
        mentorDTO.setEmail(mentor.getEmail());
        mentorDTO.setPhoneNumber(mentor.getPhoneNumber());
        mentorDTO.setDisplayName(mentor.getDisplayName());
        return mentorDTO;
    }

    @Override
    public List<Mentor> bulkConvertToEntity(List<MentorDto> mentorDtos) {
        return mentorDtos.stream().map(this::convertToEntity).collect(Collectors.toList());
    }

    @Override
    public Mentor convertToEntity(MentorDto mentorDTO) {
        final Mentor mentor = new Mentor();
        mentor.setFirstName(mentorDTO.getFirstName());
        mentor.setLastName(mentorDTO.getLastName());
        mentor.setEmail(mentorDTO.getEmail());
        mentor.setPhoneNumber(mentorDTO.getPhoneNumber());
        mentor.setDisplayName(mentorDTO.getDisplayName());
        return mentor;
    }
}

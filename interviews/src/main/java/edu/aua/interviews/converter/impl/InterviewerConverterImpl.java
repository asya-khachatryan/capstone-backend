package edu.aua.interviews.converter.impl;

import edu.aua.interviews.converter.InterviewConverter;
import edu.aua.interviews.converter.InterviewerConverter;
import edu.aua.interviews.persistance.Interviewer;
import edu.aua.interviews.persistance.dto.InterviewerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InterviewerConverterImpl implements InterviewerConverter {

    @Autowired
    private InterviewConverter interviewConverter;

    @Override
    public List<InterviewerDTO> bulkConvertToDTO(List<Interviewer> users) {
        return users.stream().map(this::convertToDTO).collect(Collectors.toList());

    }

    @Override
    public InterviewerDTO convertToDTO(Interviewer user) {
        if (user == null) {
            return null;
        }
        final InterviewerDTO interviewerDTO = new InterviewerDTO();
        interviewerDTO.setId(user.getId());
        interviewerDTO.setFirstName(user.getFirstName());
        interviewerDTO.setLastName(user.getLastName());
        interviewerDTO.setEmail(user.getEmail());
        return interviewerDTO;
    }

    @Override
    public List<Interviewer> bulkConvertToEntity(List<InterviewerDTO> userDTOS) {
        return userDTOS.stream().map(this::convertToEntity).collect(Collectors.toList());

    }

    @Override
    public Interviewer convertToEntity(InterviewerDTO interviewerDTO) {
        if (interviewerDTO == null) {
            return null;
        }
        final Interviewer interviewer = new Interviewer();
        interviewer.setFirstName(interviewerDTO.getFirstName());
        interviewer.setLastName(interviewerDTO.getLastName());
        interviewer.setEmail(interviewerDTO.getEmail());
        return interviewer;
    }
}

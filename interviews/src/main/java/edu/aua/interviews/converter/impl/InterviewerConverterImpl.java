package edu.aua.interviews.converter.impl;

import edu.aua.interviews.converter.InterviewerConverter;
import edu.aua.interviews.persistance.Interviewer;
import edu.aua.interviews.persistance.dto.InterviewerDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InterviewerConverterImpl implements InterviewerConverter {
    @Override
    public List<InterviewerDTO> bulkConvertToDTO(List<Interviewer> users) {
        return users.stream().map(this::convertToDTO).toList();
    }

    @Override
    public InterviewerDTO convertToDTO(Interviewer interviewer) {
        if (interviewer == null) {
            return null;
        }
        final InterviewerDTO interviewerDTO = new InterviewerDTO();
        interviewerDTO.setId(interviewer.getId());
        interviewerDTO.setFirstName(interviewer.getFirstName());
        interviewerDTO.setLastName(interviewer.getLastName());
        interviewerDTO.setEmail(interviewer.getEmail());
        interviewerDTO.setPosition(interviewer.getPosition());
        return interviewerDTO;
    }

    @Override
    public List<Interviewer> bulkConvertToEntity(List<InterviewerDTO> userDTOS) {
        return userDTOS.stream().map(this::convertToEntity).toList();
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
        interviewer.setPosition(interviewerDTO.getPosition());
        return interviewer;
    }
}

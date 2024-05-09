package edu.aua.interviews.converter;

import edu.aua.interviews.persistance.Interviewer;
import edu.aua.interviews.persistance.dto.InterviewerDTO;

import java.util.List;

public interface InterviewerConverter {
    List<InterviewerDTO> bulkConvertToDTO(List<Interviewer> users);

    InterviewerDTO convertToDTO(Interviewer user);

    List<Interviewer> bulkConvertToEntity(List<InterviewerDTO> userDTOS);

    Interviewer convertToEntity(InterviewerDTO userDTO);
}

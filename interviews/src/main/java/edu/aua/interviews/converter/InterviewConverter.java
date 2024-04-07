package edu.aua.interviews.converter;

import edu.aua.interviews.persistance.entity.interview.Interview;
import edu.aua.interviews.service.dto.InterviewRequestDTO;
import edu.aua.interviews.service.dto.InterviewResponseDTO;

import java.util.List;

public interface InterviewConverter {

    List<InterviewResponseDTO> bulkConvertToDTO(List<Interview> interviews);

    InterviewResponseDTO convertToDTO(Interview interview);

    List<Interview> bulkConvertToEntity(List<InterviewRequestDTO> interviews);

    Interview convertToEntity(InterviewRequestDTO interviewRequestDTO);
}

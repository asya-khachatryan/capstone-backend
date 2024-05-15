package edu.aua.interviews.service;

import edu.aua.interviews.persistance.Interview;
import edu.aua.interviews.persistance.dto.CalendlyEventDTO;
import edu.aua.interviews.persistance.dto.InterviewRequestDTO;
import edu.aua.interviews.persistance.dto.InterviewResponseDTO;

import java.util.List;

public interface InterviewService {
    InterviewResponseDTO startInterviewPreparation(InterviewRequestDTO interviewRequestDTO, String hrManagerUsername);

    Boolean addEvent(CalendlyEventDTO eventDTO);

    List<Interview> findTalentAllInterviews(Long id);

    List<Interview> findAll();
}

package edu.aua.interviews.service;

import edu.aua.interviews.persistance.Interview;
import edu.aua.interviews.persistance.dto.CalendlyEventDTO;
import edu.aua.interviews.persistance.dto.InterviewRequestDTO;
import edu.aua.interviews.persistance.dto.InterviewResponseDTO;
import edu.aua.talents.persistance.Talent;

import java.util.List;

public interface InterviewService {
    InterviewResponseDTO startInterviewPreparation(InterviewRequestDTO interviewRequestDTO, String hrManagerUsername);

    Boolean addEvent(CalendlyEventDTO eventDTO);

    List<Interview> findTalentAllInterviews(Long id);

    List<Interview> findAll();

    Interview submitFeedback(Long id, String feedback, String username);

    Interview findByIdOrThrow(Long id);
}

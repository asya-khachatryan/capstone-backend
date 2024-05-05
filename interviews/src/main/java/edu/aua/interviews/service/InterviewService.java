package edu.aua.interviews.service;

import edu.aua.interviews.excaption.TalentNotFoundException;
import edu.aua.interviews.persistance.Interview;
import edu.aua.interviews.persistance.dto.CalendlyEventDTO;
import edu.aua.interviews.persistance.dto.InterviewRequestDTO;

import java.util.List;

public interface InterviewService {
    Boolean startInterviewPreparation(InterviewRequestDTO interviewRequestDTO);

    String addEvent(CalendlyEventDTO eventDTO);

    List<Interview> findTalentAllInterviews(Long id) throws TalentNotFoundException;
}

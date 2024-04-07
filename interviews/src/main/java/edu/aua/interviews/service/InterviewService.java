package edu.aua.interviews.service;

import edu.aua.interviews.excaption.TalentNotFoundException;
import edu.aua.interviews.persistance.entity.interview.Interview;
import edu.aua.interviews.service.dto.InterviewEventDTO;
import edu.aua.interviews.service.dto.InterviewRequestDTO;

import java.util.List;

public interface InterviewService {
    String  preparationInterview(InterviewRequestDTO interviewRequestDTO);

    String  addEvent(InterviewEventDTO eventDTO);

    List<Interview> findTalentAllInterviews(Long id) throws TalentNotFoundException;

}

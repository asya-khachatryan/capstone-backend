package edu.aua.interviews.service;

import edu.aua.interviews.persistance.Interviewer;
import edu.aua.interviews.persistance.dto.InterviewerDTO;

public interface InterviewerService {

    Interviewer findByIdOrThrow(Long id);

    Interviewer create(InterviewerDTO interviewerDTO);

    Interviewer update(Long id, InterviewerDTO interviewerDTO);

    boolean deleteById(Long id);
}

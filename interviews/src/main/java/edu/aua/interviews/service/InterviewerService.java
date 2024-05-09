package edu.aua.interviews.service;

import edu.aua.interviews.persistance.Interviewer;

public interface InterviewerService {

    Interviewer findById(Long id);
}

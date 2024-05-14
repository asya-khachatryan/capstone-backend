package edu.aua.interviews.service;

import edu.aua.interviews.persistance.Interviewer;
import edu.aua.interviews.persistance.dto.InterviewerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InterviewerService {

    Page<Interviewer> findAll(Pageable page);

    Interviewer findByIdOrThrow(Long id);

    Interviewer create(InterviewerDTO interviewerDTO);

    Interviewer update(Long id, InterviewerDTO interviewerDTO);

    boolean deleteById(Long id);
}

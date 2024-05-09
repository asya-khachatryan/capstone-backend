package edu.aua.interviews.service.impl;

import edu.aua.common.exception.NotFoundException;
import edu.aua.interviews.persistance.Interviewer;
import edu.aua.interviews.repositories.InterviewerRepository;
import edu.aua.interviews.service.InterviewerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InterviewerServiceImpl implements InterviewerService {

    private final InterviewerRepository interviewerRepository;

    @Override
    @Transactional(readOnly = true)
    public Interviewer findById(Long id) {
        return interviewerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No user found by this id", id));
    }
}


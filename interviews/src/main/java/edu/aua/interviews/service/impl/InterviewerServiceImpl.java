package edu.aua.interviews.service.impl;

import edu.aua.common.exception.NotFoundException;
import edu.aua.interviews.converter.InterviewerConverter;
import edu.aua.interviews.persistance.Interviewer;
import edu.aua.interviews.persistance.dto.InterviewerDTO;
import edu.aua.interviews.repositories.InterviewerRepository;
import edu.aua.interviews.service.InterviewerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InterviewerServiceImpl implements InterviewerService {

    private final InterviewerRepository repository;
    private final InterviewerConverter converter;

    @Override
    @Transactional(readOnly = true)
    public Interviewer findByIdOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("No user found by this id", id));
    }

    @Override
    @Transactional
    public Interviewer create(InterviewerDTO interviewerDTO) {
        return repository.save(converter.convertToEntity(interviewerDTO));
    }

    @Override
    @Transactional
    public Interviewer update(Long id, InterviewerDTO interviewerDTO) {
        final Interviewer interviewer = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("No interviewer found by this id", id));
        interviewer.setFirstName(interviewerDTO.getFirstName());
        interviewer.setLastName(interviewerDTO.getLastName());
        interviewer.setEmail(interviewerDTO.getEmail());
        interviewer.setPosition(interviewerDTO.getPosition());
        return repository.save(interviewer);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("No talent found by this id", id);
        }
        repository.deleteById(id);
        return true;
    }
}


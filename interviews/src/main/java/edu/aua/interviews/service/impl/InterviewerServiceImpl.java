package edu.aua.interviews.service.impl;

import edu.aua.common.exception.NotFoundException;
import edu.aua.interviews.converter.InterviewerConverter;
import edu.aua.interviews.persistance.Interviewer;
import edu.aua.interviews.persistance.dto.InterviewerDTO;
import edu.aua.interviews.repositories.InterviewerRepository;
import edu.aua.interviews.service.InterviewerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InterviewerServiceImpl implements InterviewerService {

    private final InterviewerRepository repository;
    private final InterviewerConverter converter;

    @Override
    public Page<Interviewer> findAll(Pageable page) {
        return repository.findAll(page);
    }

    @Override
    @Transactional(readOnly = true)
    public Interviewer findByIdOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("No interviewer found by this id %s", id)));
    }

    @Override
    @Transactional
    public Interviewer create(InterviewerDTO interviewerDTO) {
        return repository.save(converter.convertToEntity(interviewerDTO));
    }

    @Override
    @Transactional
    public Interviewer update(Long id, InterviewerDTO interviewerDTO) {
        final Interviewer interviewer = findByIdOrThrow(id);
        interviewer.setFirstName(interviewerDTO.getFirstName());
        interviewer.setLastName(interviewerDTO.getLastName());
        interviewer.setEmail(interviewerDTO.getEmail());
        interviewer.setPosition(interviewerDTO.getPosition());
        return repository.save(interviewer);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        findByIdOrThrow(id);
        repository.deleteById(id);
        return true;
    }
}


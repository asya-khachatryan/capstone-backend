package edu.aua.interviews.service.impl;

import edu.aua.interviews.excaption.SpecializationNotFoundException;
import edu.aua.interviews.persistance.entity.Specialization;
import edu.aua.interviews.persistance.repositories.SpecializationRepository;
import edu.aua.interviews.service.SpecializationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SpecializationServiceImpl implements SpecializationService {
    private final SpecializationRepository specializationRepository;

    @Override
    @Transactional(readOnly = true)
    public Specialization findById(Long id) {
        return this.specializationRepository.findById(id)
                .orElseThrow(() -> new SpecializationNotFoundException("No specialization found with this id",id));
    }


}

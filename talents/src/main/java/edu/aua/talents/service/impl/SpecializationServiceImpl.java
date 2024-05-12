package edu.aua.talents.service.impl;


import edu.aua.common.exception.NotFoundException;
import edu.aua.talents.converter.SpecializationConverter;
import edu.aua.talents.repository.SpecializationRepository;
import edu.aua.talents.persistance.Specialization;
import edu.aua.talents.service.SpecializationService;
import edu.aua.talents.persistance.dto.SpecializationRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class SpecializationServiceImpl implements SpecializationService {

    private final SpecializationRepository specializationRepository;
    private final SpecializationConverter specializationConverter;

    public SpecializationServiceImpl(SpecializationRepository specializationRepository, SpecializationConverter specializationConverter) {
        this.specializationRepository = specializationRepository;
        this.specializationConverter = specializationConverter;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Specialization> findALl() {
        log.info("In findAll Specialization requested to get all specializations");
        return this.specializationRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Specialization findById(Long id) {
        log.info("In findById Specialization requested to get the specialization with id {}", id);
        return this.specializationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No specialization found with this id", id));
    }

    @Override
    @Transactional
    public Specialization create(SpecializationRequestDTO specializationDTO) {
        log.info("Requested to create a specialization");
        final Specialization specialization = specializationConverter.convertToEntity(specializationDTO);
        specialization.setSpecializationType(specializationDTO.getSpecializationClientType());
        log.info("In create Specialization specialization successfully created");
        return specializationRepository.save(specialization);
    }

    @Override
    @Transactional
    public Specialization update(Long id, SpecializationRequestDTO specializationDTO) {
        log.info("Requested to update a specialization with id {}", id);
        final Specialization specialization = this.specializationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No specialization found with this id", id));
        specialization.setSpecializationType(specializationDTO.getSpecializationClientType());
        log.info("In update Specialization specialization with id {} successfully updated", id);
        return specializationRepository.save(specialization);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        log.info("Requested to delete a specialization with id {}", id);
        if (!specializationRepository.existsById(id)) {
            throw new NotFoundException("No specialization found by this id", id);
        }
        specializationRepository.deleteById(id);
        log.info("In deleteById Specialization specialization with id {} successfully deleted", id);
        return true;
    }
}

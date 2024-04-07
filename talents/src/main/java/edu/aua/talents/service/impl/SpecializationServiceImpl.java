package edu.aua.talents.service.impl;


import edu.aua.talents.converter.SpecializationConverter;
import edu.aua.talents.exception.SpecializationNotFoundException;
import edu.aua.talents.persistance.SpecializationRepository;
import edu.aua.talents.persistance.entity.Specialization;
import edu.aua.talents.service.SpecializationService;
import edu.aua.talents.service.dto.SpecializationRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SpecializationServiceImpl implements SpecializationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TalentServiceImpl.class);

    private final SpecializationRepository specializationRepository;
    private final SpecializationConverter specializationConverter;

    public SpecializationServiceImpl(SpecializationRepository specializationRepository, SpecializationConverter specializationConverter) {
        this.specializationRepository = specializationRepository;
        this.specializationConverter = specializationConverter;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Specialization> findALl() {
        LOGGER.info("In findAll Specialization requested to get all specializations");
        return this.specializationRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Specialization findById(Long id) {
        LOGGER.info("In findById Specialization requested to get the specialization with id {}", id);
        return this.specializationRepository.findById(id)
                .orElseThrow(() -> new SpecializationNotFoundException("No specialization found with this id", id));
    }

    @Override
    @Transactional
    public Specialization create(SpecializationRequestDTO specializationDTO) {
        LOGGER.info("Requested to create a specialization");
        final Specialization specialization = specializationConverter.convertToEntity(specializationDTO);
        specialization.setSpecializationType(specializationDTO.getSpecializationClientType());
        LOGGER.info("In create Specialization specialization successfully created");
        return specializationRepository.save(specialization);
    }

    @Override
    @Transactional
    public Specialization update(Long id, SpecializationRequestDTO specializationDTO) {
        LOGGER.info("Requested to update a specialization with id {}", id);
        final Specialization specialization = this.specializationRepository.findById(id)
                .orElseThrow(() -> new SpecializationNotFoundException("No specialization found with this id", id));
        specialization.setSpecializationType(specializationDTO.getSpecializationClientType());
        LOGGER.info("In update Specialization specialization with id {} successfully updated", id);
        return specializationRepository.save(specialization);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        LOGGER.info("Requested to delete a specialization with id {}", id);
        if (!specializationRepository.existsById(id)) {
            throw new SpecializationNotFoundException("No specialization found by this id", id);
        }
        specializationRepository.deleteById(id);
        LOGGER.info("In deleteById Specialization specialization with id {} successfully deleted", id);
        return true;
    }
}

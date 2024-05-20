package edu.aua.common.service;


import edu.aua.common.converter.SpecializationConverter;
import edu.aua.common.exception.NotFoundException;
import edu.aua.common.model.Specialization;
import edu.aua.common.model.SpecializationDTO;
import edu.aua.common.repository.SpecializationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
    public Page<SpecializationDTO> findAll(Pageable page) {
        Page<Specialization> all = specializationRepository.findAll(page);
        return new PageImpl<>(specializationConverter.bulkConvertToDTO(all.getContent()),
                all.getPageable(), all.getTotalElements());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Specialization> findALl() {
        log.info("In findAll Specialization requested to get all specializations");
        return this.specializationRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Specialization findByIdOrThrow(Long id) {
        log.info("In findById Specialization requested to get the specialization with id {}", id);
        return this.specializationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("No specialization found with this id %s", id)));
    }

    @Override
    @Transactional
    public Specialization create(SpecializationDTO specializationDTO) {
        log.info("Requested to create a specialization");
        final Specialization specialization = specializationConverter.convertToEntity(specializationDTO);
        specialization.setSpecializationName(specializationDTO.getSpecializationName());
        specialization.setActive(specializationDTO.isActive());
        log.info("In create Specialization specialization successfully created");
        return specializationRepository.save(specialization);
    }

    @Override
    @Transactional
    public Specialization update(Long id, SpecializationDTO specializationDTO) {
        log.info("Requested to update a specialization with id {}", id);
        final Specialization specialization = findByIdOrThrow(id);
        specialization.setSpecializationName(specializationDTO.getSpecializationName());
        specialization.setActive(specializationDTO.isActive());
        log.info("In update Specialization specialization with id {} successfully updated", id);
        return specializationRepository.save(specialization);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        log.info("Requested to delete a specialization with id {}", id);
        findByIdOrThrow(id);
        specializationRepository.deleteById(id);
        log.info("In deleteById Specialization specialization with id {} successfully deleted", id);
        return true;
    }
}

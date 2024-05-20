package edu.aua.common.service;

import edu.aua.common.model.Specialization;
import edu.aua.common.model.SpecializationDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SpecializationService {

    Page<SpecializationDTO> findAll(Pageable page);

    List<Specialization> findALl();

    Specialization findByIdOrThrow(Long id);

    Specialization create(SpecializationDTO specializationDTO);

    Specialization update(Long id, SpecializationDTO specializationDTO);

    boolean deleteById(Long id);
}

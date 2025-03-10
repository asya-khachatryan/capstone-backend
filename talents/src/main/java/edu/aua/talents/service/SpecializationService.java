package edu.aua.talents.service;

import edu.aua.talents.persistance.entity.Specialization;
import edu.aua.talents.service.dto.SpecializationRequestDTO;

import java.util.List;

public interface SpecializationService {

    List<Specialization> findALl();

    Specialization findById(Long id);

    Specialization create(SpecializationRequestDTO specializationDTO);

    Specialization update(Long id, SpecializationRequestDTO specializationDTO);

    boolean deleteById(Long id);

}

package edu.aua.common.service;

import edu.aua.common.model.Specialization;
import edu.aua.common.model.SpecializationDTO;

import java.util.List;

public interface SpecializationService {

    List<Specialization> findALl();

    Specialization findById(Long id);

    Specialization create(SpecializationDTO specializationDTO);

    Specialization update(Long id, SpecializationDTO specializationDTO);

    boolean deleteById(Long id);
}

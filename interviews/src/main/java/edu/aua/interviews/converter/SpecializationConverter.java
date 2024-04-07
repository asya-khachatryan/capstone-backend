package edu.aua.interviews.converter;

import edu.aua.interviews.persistance.entity.Specialization;
import edu.aua.interviews.service.dto.SpecializationDTO;

import java.util.List;

public interface SpecializationConverter {
    List<SpecializationDTO> bulkConvertToDTO(List<Specialization> specializations);

    SpecializationDTO convertToDTO(Specialization specialization);

    List<Specialization> bulkConvertToEntity(List<SpecializationDTO> specializationDTOList);

    Specialization convertToEntity(SpecializationDTO specializationDTO);
}

package edu.aua.talents.converter;

import edu.aua.talents.persistance.entity.Specialization;
import edu.aua.talents.service.dto.SpecializationRequestDTO;
import edu.aua.talents.service.dto.SpecializationResponseDTO;

import java.util.List;

public interface SpecializationConverter {

    List<SpecializationResponseDTO> bulkConvertToDTO(List<Specialization> specializations);

    SpecializationResponseDTO convertToDTO(Specialization specialization);

    List<Specialization> bulkConvertToEntity(List<SpecializationRequestDTO> specializationRequestDTOList);

    Specialization convertToEntity(SpecializationRequestDTO specializationRequestDTO);

}

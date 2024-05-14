package edu.aua.common.converter;

import edu.aua.common.model.Specialization;
import edu.aua.common.model.SpecializationDTO;

import java.util.List;

public interface SpecializationConverter {

    List<SpecializationDTO> bulkConvertToDTO(List<Specialization> specializations);

    SpecializationDTO convertToDTO(Specialization specialization);

    List<Specialization> bulkConvertToEntity(List<SpecializationDTO> specializationRequestDTOList);

    Specialization convertToEntity(SpecializationDTO specializationRequestDTO);

}

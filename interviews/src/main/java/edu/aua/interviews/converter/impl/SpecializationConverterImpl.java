package edu.aua.interviews.converter.impl;

import edu.aua.interviews.converter.SpecializationConverter;
import edu.aua.interviews.persistance.entity.Specialization;
import edu.aua.interviews.service.dto.SpecializationDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SpecializationConverterImpl implements SpecializationConverter {
    @Override
    public List<SpecializationDTO> bulkConvertToDTO(List<Specialization> specializations) {
        return specializations.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public SpecializationDTO convertToDTO(Specialization specialization) {
        if (specialization == null) {
            return null;
        }
        final SpecializationDTO specializationDTO = new SpecializationDTO();
        specializationDTO.setId(specialization.getId());
        specializationDTO.setSpecializationType(specialization.getSpecializationType());
        return specializationDTO;
    }

    @Override
    public List<Specialization> bulkConvertToEntity(List<SpecializationDTO> specializationDTOList) {
        return specializationDTOList.stream().map(this::convertToEntity).collect(Collectors.toList());
    }

    @Override
    public Specialization convertToEntity(SpecializationDTO specializationDTO) {
        final Specialization specialization = new Specialization();
        specialization.setSpecializationType(specializationDTO.getSpecializationType());
        return specialization;
    }
}

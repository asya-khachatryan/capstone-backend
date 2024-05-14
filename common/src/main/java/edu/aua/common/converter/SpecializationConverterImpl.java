package edu.aua.common.converter;

import edu.aua.common.model.Specialization;
import edu.aua.common.model.SpecializationDTO;
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
        final SpecializationDTO specializationDTO = new SpecializationDTO();
        specializationDTO.setId(specialization.getId());
        specializationDTO.setSpecializationName(specialization.getSpecializationName());
        specializationDTO.setActive(specialization.isActive());
        return specializationDTO;
    }

    @Override
    public List<Specialization> bulkConvertToEntity(List<SpecializationDTO> specializationRequestDTOList) {
        return specializationRequestDTOList.stream().map(this::convertToEntity).collect(Collectors.toList());
    }

    @Override
    public Specialization convertToEntity(SpecializationDTO specializationRequestDTO) {
        final Specialization specialization = new Specialization();
        specialization.setSpecializationName(specializationRequestDTO.getSpecializationName());
        specialization.setActive(specializationRequestDTO.isActive());
        return specialization;
    }
}

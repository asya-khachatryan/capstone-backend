package edu.aua.talents.converter.impl;

import edu.aua.talents.converter.SpecializationConverter;
import edu.aua.talents.converter.TalentConverter;
import edu.aua.talents.persistance.entity.Talent;
import edu.aua.talents.service.SpecializationService;
import edu.aua.talents.service.dto.TalentRequestDTO;
import edu.aua.talents.service.dto.TalentResponseDTO;
import edu.aua.talents.service.enums.TalentStatusClientType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TalentConverterImpl implements TalentConverter {

    private final SpecializationConverter specializationConverter;
    private final SpecializationService specializationService;

    public TalentConverterImpl(SpecializationConverter specializationConverter, SpecializationService specializationService) {
        this.specializationConverter = specializationConverter;
        this.specializationService = specializationService;
    }

    @Override
    public List<TalentResponseDTO> bulkConvertToDTO(List<Talent> talents) {
        return talents.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public TalentResponseDTO convertToDTO(Talent talent) {
        final TalentResponseDTO talentResponseDTO = new TalentResponseDTO();
        talentResponseDTO.setId(talent.getId());
        talentResponseDTO.setName(talent.getName());
        talentResponseDTO.setSurname(talent.getSurname());
        talentResponseDTO.setEmail(talent.getEmail());
        talentResponseDTO.setPhoneNumber(talent.getPhoneNumber());
        talentResponseDTO.setSpecialization(specializationConverter.convertToDTO(talent.getSpecialization()));
        talentResponseDTO.setTalentStatusClientType(TalentStatusClientType.valueOf(talent.getTalentStatus().name()));
        talentResponseDTO.setCvFileName(talent.getCvFileName());
        return talentResponseDTO;
    }

    @Override
    public List<Talent> bulkConvertToEntity(List<TalentRequestDTO> talents) {
        return talents.stream().map(this::convertToEntity).collect(Collectors.toList());
    }

    @Override
    public Talent convertToEntity(TalentRequestDTO talentDTO) {
        if (talentDTO == null) {
            return null;
        }
        final Talent talent = new Talent();
        talent.setName(talentDTO.getName());
        talent.setSurname(talentDTO.getSurname());
        talent.setEmail(talentDTO.getEmail());
        talent.setPhoneNumber(talentDTO.getPhoneNumber());
        //TODO question
        talent.setSpecialization(specializationService.findById(talentDTO.getSpecializationId()));
        return talent;
    }
}

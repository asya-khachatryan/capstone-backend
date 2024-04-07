package edu.aua.interviews.converter.impl;

import edu.aua.interviews.converter.TalentConverter;
import edu.aua.interviews.persistance.entity.Talent;
import edu.aua.interviews.persistance.enums.TalentStatus;
import edu.aua.interviews.service.dto.TalentDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TalentConverterImpl implements TalentConverter {

    @Override
    public List<TalentDTO> bulkConvertToDTO(List<Talent> talents) {
        return talents.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public TalentDTO convertToDTO(Talent talent) {
        final TalentDTO talentDTO = new TalentDTO();
        talentDTO.setId(talent.getId());
        talentDTO.setName(talent.getName());
        talentDTO.setSurname(talent.getSurname());
        talentDTO.setEmail(talent.getEmail());
        talentDTO.setPhoneNumber(talent.getPhoneNumber());
        talentDTO.setOverallScore(talent.getOverallScore());
        talentDTO.setTalentStatus(TalentStatus.valueOf(talent.getTalentStatus().name()));

        return talentDTO;
    }

    @Override
    public List<Talent> bulkConvertToEntity(List<TalentDTO> talents) {
        return talents.stream().map(this::convertToEntity).collect(Collectors.toList());
    }

    @Override
    public Talent convertToEntity(TalentDTO talentDTO) {
        final Talent talent = new Talent();
        talent.setId(talentDTO.getId());
        talent.setName(talentDTO.getName());
        talent.setSurname(talentDTO.getSurname());
        talent.setEmail(talentDTO.getEmail());
        talent.setPhoneNumber(talentDTO.getPhoneNumber());
        talent.setTalentStatus(TalentStatus.valueOf(talentDTO.getTalentStatus().name()));

        return talent;
    }
}

package edu.aua.interviews.converter;

import edu.aua.interviews.persistance.entity.Talent;
import edu.aua.interviews.service.dto.TalentDTO;

import java.util.List;

public interface TalentConverter {
    List<TalentDTO> bulkConvertToDTO(List<Talent> talents);

    TalentDTO convertToDTO(Talent talent);

    List<Talent> bulkConvertToEntity(List<TalentDTO> talents);

    Talent convertToEntity(TalentDTO talentDTO);
}

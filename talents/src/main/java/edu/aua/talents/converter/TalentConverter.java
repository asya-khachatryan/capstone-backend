package edu.aua.talents.converter;

import edu.aua.talents.persistance.entity.Talent;
import edu.aua.talents.service.dto.TalentRequestDTO;
import edu.aua.talents.service.dto.TalentResponseDTO;

import java.util.List;

public interface TalentConverter {

    List<TalentResponseDTO> bulkConvertToDTO(List<Talent> talents);

    TalentResponseDTO convertToDTO(Talent talent);

    List<Talent> bulkConvertToEntity(List<TalentRequestDTO> talents);

    Talent convertToEntity(TalentRequestDTO talentRequestDTO);

}

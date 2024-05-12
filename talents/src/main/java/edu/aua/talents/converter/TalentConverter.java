package edu.aua.talents.converter;

import edu.aua.talents.persistance.Talent;
import edu.aua.talents.persistance.dto.TalentRequestDTO;
import edu.aua.talents.persistance.dto.TalentResponseDTO;

import java.util.List;

public interface TalentConverter {

    List<TalentResponseDTO> bulkConvertToDTO(List<Talent> talents);

    TalentResponseDTO convertToDTO(Talent talent);

    List<Talent> bulkConvertToEntity(List<TalentRequestDTO> talents);

    Talent convertToEntity(TalentRequestDTO talentRequestDTO);

}

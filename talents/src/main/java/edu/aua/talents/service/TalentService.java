package edu.aua.talents.service;

import edu.aua.talents.persistance.Talent;
import edu.aua.talents.persistance.dto.TalentRequestDTO;
import edu.aua.talents.persistance.dto.TalentResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface TalentService {

    List<Talent> findAll();

    Page<TalentResponseDTO> findAll(Pageable page);

    Talent findByIdOrThrow(Long id);

    Talent findByEmailOrThrow(String email);

    Boolean existsById(Long id);

    Talent create(TalentRequestDTO talentDTO);

    Talent update(Long id, TalentRequestDTO talentDTO);

    boolean deleteById(Long id);

    TalentResponseDTO updateStatus(Long id, TalentRequestDTO dto);

    TalentResponseDTO updateStatus(Long id, String hrManagerUsername, TalentRequestDTO dto);


    List<Talent> findBySpecializationId(Long specializationId);

    String addCVForTalent(Long id, MultipartFile file) throws IOException;

    List<Talent> findInterviewees();

    List<Talent> searchTalent(String query, String type);

    String getCvUrl(Long id);


}

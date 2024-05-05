package edu.aua.talents.service;

import edu.aua.talents.exception.TalentNotFoundException;
import edu.aua.talents.persistance.entity.*;
import edu.aua.talents.service.dto.TalentRequestDTO;
import edu.aua.talents.service.dto.TalentResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

public interface TalentService {

    List<Talent> findAll();

    Page<Talent> findAll(Pageable page);

    Talent findByIdOrThrow(Long id) throws TalentNotFoundException;

    Talent findByEmailOrThrow(String email) throws TalentNotFoundException;

    Boolean existsById(Long id);

    Talent create(TalentRequestDTO talentDTO);

    Talent update(Long id, TalentRequestDTO talentDTO) throws TalentNotFoundException;

    boolean deleteById(Long id);

    TalentResponseDTO updateStatus(TalentRequestDTO dto);

    List<Talent> findBySpecializationId(Long specializationId);

    String addCVForTalent(Long id, MultipartFile file) throws IOException ;

    List<Talent> findInterviewees();

    List<Talent> searchTalent(String query, String type);

    String getCvUrl(Long id);



}

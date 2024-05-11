package edu.aua.talents.service.impl;

import edu.aua.common.exception.NotFoundException;
import edu.aua.common.service.EmailService;
import edu.aua.common.util.TimeService;
import edu.aua.talents.converter.TalentConverter;
import edu.aua.talents.persistance.Talent;
import edu.aua.talents.persistance.TalentStatus;
import edu.aua.talents.repository.TalentRepository;
import edu.aua.talents.service.AmazonClientService;
import edu.aua.talents.service.SpecializationService;
import edu.aua.talents.service.TalentService;
import edu.aua.talents.service.dto.TalentRequestDTO;
import edu.aua.talents.service.dto.TalentResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static edu.aua.talents.utils.EmailGenerator.generateEmail;

@Service
@Slf4j
public class TalentServiceImpl implements TalentService {
    private final TalentRepository talentRepository;
    private final SpecializationService specializationService;
    private final TalentConverter talentConverter;
    private final EmailService emailService;
    private final AmazonClientService amazonClientService;

    public TalentServiceImpl(TalentRepository talentRepository, SpecializationService specializationService,
                             TalentConverter talentConverter,
                             EmailService emailService, AmazonClientService amazonClientService) {
        this.talentRepository = talentRepository;
        this.specializationService = specializationService;
        this.talentConverter = talentConverter;
        this.emailService = emailService;
        this.amazonClientService = amazonClientService;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Talent> findAll() {
        log.info("In findAll Talent requested to get all talents");
        return talentRepository.findAll();
    }

    @Override
    public Page<TalentResponseDTO> findAll(Pageable page) {
        Page<Talent> all = talentRepository.findAll(page);
        return new PageImpl<>(talentConverter.bulkConvertToDTO(all.getContent()),
                all.getPageable(), all.getTotalElements());
    }

    @Override
    @Transactional(readOnly = true)
    public Talent findByIdOrThrow(Long id) {
        log.info("In findById Talent requested to get the talent with id {}", id);
        return talentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No talent found by this id", id));
    }

    @Override
    @Transactional(readOnly = true)
    public Talent findByEmailOrThrow(String email) throws NotFoundException {
        return talentRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("No talent found by this email", email));
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean existsById(Long id) {
        return talentRepository.existsById(id);
    }

    @Override
    @Transactional
    public Talent create(TalentRequestDTO talentDTO) {
        log.info("Requested to create a talent");
        final Talent talent = talentConverter.convertToEntity(talentDTO);
        talent.setTalentStatus(TalentStatus.APPLIED);
        talent.setDateApplied(TimeService.getUtcNow());
        emailService.sendEmail(generateEmail(talent));
        log.info("In create Talent talent successfully created");
        return talentRepository.save(talent);
    }

    @Override
    @Transactional
    public Talent update(Long id, TalentRequestDTO talentDTO) {
        log.info("Requested to update a talent with id {}", id);
        final Talent talent = talentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No talent found by this id", id));
        talent.setName(talentDTO.getName());
        talent.setSurname(talentDTO.getSurname());
        talent.setEmail(talentDTO.getEmail());
        talent.setPhoneNumber(talentDTO.getPhoneNumber());
        talent.setSpecialization(specializationService.findById(talentDTO.getSpecializationId()));
        log.info("In update Talent talent with id {} successfully updated", id);
        return talentRepository.save(talent);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        log.info("Requested to delete a talent with id {}", id);
        if (!talentRepository.existsById(id)) {
            throw new NotFoundException("No talent found by this id", id);
        }
        talentRepository.deleteById(id);
        log.info("In deleteById Talent talent with id {} successfully deleted", id);
        return true;
    }

    @Override
    public TalentResponseDTO updateStatus(TalentRequestDTO talentRequestDTO) {
        String email = talentRequestDTO.getEmail();
        TalentStatus status = talentRequestDTO.getStatus();
        log.info("Requested to update status to {} of a talent with email {}", status, email);
        final Talent talent = talentRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("No talent found by this email", email));
        talent.setTalentStatus(TalentStatus.valueOf(status.name()));
        log.info("In updateStatus Talent the status of talent with email {} successfully updated to {}", email, status);
        final Talent savedTalent = talentRepository.save(talent);
        return talentConverter.convertToDTO(savedTalent);

    }

    @Override
    public List<Talent> findBySpecializationId(Long specializationId) {
        log.info("In findBySpecializationId Talent requested to get all talents with specialization id {}", specializationId);
        return talentRepository.findBySpecializationId(specializationId);
    }

    @Override
    public String addCVForTalent(Long id, MultipartFile file) throws IOException {
        final Talent talent = talentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No talent found by this id", id));
        String cvFileName = amazonClientService.uploadFile(file, talent);
        talent.setCvFileName(cvFileName);
        talentRepository.save(talent);
        return talent.getCvFileName();
    }

    @Override
    public List<Talent> findInterviewees() {
        return talentRepository.findInterviewees();
    }

    //todo
    @Override
    public List<Talent> searchTalent(String query, String type) {
        if (type.equals("Application")) {
            return talentRepository.findTalentLike(query);
        } else {
            return talentRepository.findIntervieweeLike(query);
        }
    }

    @Override
    public String getCvUrl(Long id) {
        Talent talent = findByIdOrThrow(id);
        return amazonClientService.getUrlByFileName(talent.getCvFileName());
    }
}

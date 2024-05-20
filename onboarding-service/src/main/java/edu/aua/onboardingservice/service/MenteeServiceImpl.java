package edu.aua.onboardingservice.service;

import edu.aua.auth.persistance.User;
import edu.aua.auth.service.UserService;
import edu.aua.common.converter.SpecializationConverter;
import edu.aua.common.exception.NotFoundException;
import edu.aua.common.model.EmailDTO;
import edu.aua.common.service.EmailService;
import edu.aua.onboardingservice.converter.MenteeConverter;
import edu.aua.onboardingservice.persistance.Mentee;
import edu.aua.onboardingservice.persistance.MenteeDto;
import edu.aua.onboardingservice.repository.MenteeRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static edu.aua.onboardingservice.util.EmailMessages.ONBOARDING_DOCUMENT_EMAIL_SUBJECT;
import static edu.aua.onboardingservice.util.EmailMessages.generateOnboardingEmailText;

@Service
@RequiredArgsConstructor
public class MenteeServiceImpl implements MenteeService {

    private static final Logger log = LoggerFactory.getLogger(MenteeServiceImpl.class);

    private final MenteeRepository menteeRepository;
    private final MenteeConverter menteeConverter;
    private final EmailService emailService;
    private final UserService userService;
    private final SpecializationConverter specializationConverter;


    @Override
    public Page<MenteeDto> findAll(Pageable page) {
        Page<Mentee> all = menteeRepository.findAll(page);
        return new PageImpl<>(menteeConverter.bulkConvertToDto(all.getContent()),
                all.getPageable(), all.getTotalElements());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Mentee> findALl() {
        log.info("In findAll Mentee requested to get all mentees");
        return this.menteeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Mentee findByIdOrThrow(Long id) {
        log.info("In findById Mentee requested to get the mentee with id {}", id);
        return this.menteeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("No mentee found by this id", id)));
    }

    @Override
    @Transactional
    public Mentee create(final MenteeDto menteeDto) {
        return menteeRepository.save(menteeConverter.convertToEntity(menteeDto));
    }

    @Override
    @Transactional
    public Mentee update(Long id, MenteeDto menteeDTO) {
        final Mentee mentee = findByIdOrThrow(id);
        mentee.setFirstName(menteeDTO.getFirstName());
        mentee.setLastName(menteeDTO.getLastName());
        mentee.setEmail(menteeDTO.getEmail());
        mentee.setPhoneNumber(menteeDTO.getPhoneNumber());
        mentee.setSpecialization(specializationConverter.convertToEntity(menteeDTO.getSpecialization()));
        return menteeRepository.save(mentee);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        log.info("Requested to delete a mentee with id {}", id);
        findByIdOrThrow(id);
        menteeRepository.deleteById(id);
        log.info("In deleteById Mentee mentee with id {} successfully deleted", id);
        return true;
    }

    @Override
    @Transactional
    public boolean sendOnboardingEmail(Long menteeId, String hrManagerUsername, String documentUrl) {
        User hrManager = userService.findByUsernameOrThrow(hrManagerUsername);
        Mentee mentee = findByIdOrThrow(menteeId);
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setEmailTo(mentee.getEmail());
        emailDTO.setSubject(ONBOARDING_DOCUMENT_EMAIL_SUBJECT);
        emailDTO.setText(generateOnboardingEmailText(mentee.getFirstName(), documentUrl, hrManager.getFullName()));
        emailService.sendEmail(emailDTO);
        mentee.setOnboardingDocumentSent(true);
        menteeRepository.save(mentee);
        return true;
    }

    @Override
    public List<Mentee> searchMentee(String query) {
        return menteeRepository.findMenteesLike(query);
    }
}

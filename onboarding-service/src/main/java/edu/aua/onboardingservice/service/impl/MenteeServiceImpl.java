package edu.aua.onboardingservice.service.impl;

import edu.aua.auth.persistance.User;
import edu.aua.auth.service.UserService;
import edu.aua.common.model.EmailDTO;
import edu.aua.common.service.EmailService;
import edu.aua.onboardingservice.client.jiraclient.user.dto.JiraUserDto;
import edu.aua.onboardingservice.converter.MenteeConverter;
import edu.aua.onboardingservice.exception.MenteeNotFoundException;
import edu.aua.onboardingservice.persistance.MenteeRepository;
import edu.aua.onboardingservice.persistance.entity.Mentee;
import edu.aua.onboardingservice.service.MenteeService;
import edu.aua.onboardingservice.service.MentorService;
import edu.aua.onboardingservice.service.dto.MenteeDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final MentorService mentorService;
    private final EmailService emailService;
    private final UserService userService;

    @Override
    @Transactional(readOnly = true)
    public List<Mentee> findALl() {
        log.info("In findAll Mentee requested to get all mentees");
        return this.menteeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Mentee findById(Long id) {
        log.info("In findById Mentee requested to get the mentee with id {}", id);
        return this.menteeRepository.findById(id)
                .orElseThrow(() -> new MenteeNotFoundException("No mentee found by this id", id));
    }

    @Override
    @Transactional
    public Mentee create(final MenteeDto menteeDto, final JiraUserDto jiraUser) {
        log.info("In create mentee requested to create mentee {}", menteeDto);
        menteeDto.setDisplayName(jiraUser.getDisplayName());
        menteeDto.setAccountId(jiraUser.getAccountId());
        return menteeRepository.save(buildMenteeFrom(menteeDto));
    }

    @Override
    @Transactional
    public Mentee update(Long id, MenteeDto menteeDTO) {
        final Mentee mentee = menteeRepository.findById(id)
                .orElseThrow(() -> new MenteeNotFoundException("No mentee found by this id", id));
        extractMentee(menteeDTO, mentee);
        return menteeRepository.save(mentee);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        log.info("Requested to delete a mentee with id {}", id);
        if (!menteeRepository.existsById(id)) {
            throw new MenteeNotFoundException("No mentee found by this id", id);
        }
        menteeRepository.deleteById(id);
        log.info("In deleteById Mentee mentee with id {} successfully deleted", id);
        return true;
    }

    private Mentee buildMenteeFrom(final MenteeDto menteeDTO) {
        final Mentee mentee = new Mentee();
        extractMentee(menteeDTO, mentee);
        return mentee;
    }

    private void extractMentee(final MenteeDto menteeDTO, final Mentee mentee) {
        mentee.setFirstName(menteeDTO.getFirstName());
        mentee.setLastName(menteeDTO.getLastName());
        mentee.setEmail(menteeDTO.getEmail());
        mentee.setPhoneNumber(menteeDTO.getPhoneNumber());
        mentee.setDisplayName(menteeDTO.getDisplayName());
        mentee.setAccountId(menteeDTO.getAccountId());
        mentee.setMentor(mentorService.findById(menteeDTO.getMentorId()));
    }

    @Override
    @Transactional
    public boolean sendOnboardingEmail(Long menteeId, String hrManagerUsername, String documentUrl) {
        User hrManager = userService.findByUsernameOrThrow(hrManagerUsername);
        Mentee mentee = findById(menteeId);
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setEmailTo(mentee.getEmail());
        emailDTO.setSubject(ONBOARDING_DOCUMENT_EMAIL_SUBJECT);
        emailDTO.setText(generateOnboardingEmailText(mentee.getFirstName(), documentUrl, hrManager.getFullName()));
        emailService.sendEmail(emailDTO);
        mentee.setOnboardingDocumentSent(true);
        menteeRepository.save(mentee);
        return true;
    }
}

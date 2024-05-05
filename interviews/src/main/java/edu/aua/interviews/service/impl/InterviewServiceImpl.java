package edu.aua.interviews.service.impl;

import edu.aua.common.model.EmailDTO;
import edu.aua.common.service.EmailService;
import edu.aua.interviews.converter.InterviewConverter;
import edu.aua.interviews.persistance.EmailTextType;
import edu.aua.interviews.persistance.EventType;
import edu.aua.interviews.persistance.Interview;
import edu.aua.interviews.persistance.InterviewStatus;
import edu.aua.interviews.persistance.dto.CalendlyEventDTO;
import edu.aua.interviews.persistance.dto.InterviewRequestDTO;
import edu.aua.interviews.repositories.InterviewRepository;
import edu.aua.interviews.service.EmailTextGenerator;
import edu.aua.interviews.service.InterviewService;
import edu.aua.interviews.service.UserService;
import edu.aua.talents.persistance.entity.Talent;
import edu.aua.talents.service.TalentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class InterviewServiceImpl implements InterviewService {

    private final InterviewRepository interviewRepository;
    private final InterviewConverter interviewConverter;
    private final TalentService talentService;
    private final UserService userService;
    private final EmailService emailService;
    private final EmailTextGenerator mailTextGenerator;

    @Value("${interview.flow.addEvent.selectedResponse}")
    private String selectedResponse;
    @Value("${interview.flow.addEvent.rejectedResponse}")
    private String rejectedResponse;

    @Override
    public Boolean startInterviewPreparation(InterviewRequestDTO interviewRequestDTO) {
        Interview interview = new Interview();
        interview.setTalent(talentService.findByIdOrThrow(interviewRequestDTO.getTalentID()));
        interview.setUsers(interviewRequestDTO.getUserIDs().stream().map(userService::findById).toList());
        interview.setInterviewStatus(InterviewStatus.IN_PREPARATION);
        interview.setInterviewType(interviewRequestDTO.getInterviewType());
        //todo specialization??
        String emailText = mailTextGenerator.getEmailText(interview.getTalent(), null, EmailTextType.TO_TALENT_FIRST, interview.getUrl());
        sendEmail(interview.getTalent().getEmail(), EmailTextGenerator.getSubject(), emailText);
        interviewRepository.save(interview);
        return true;
    }

    private void sendEmail(String email, String subject, String text) {
        EmailDTO emailDTO = new EmailDTO(email, subject, text);
        emailService.sendEmail(emailDTO);
    }

    @Override
    public String addEvent(CalendlyEventDTO eventDTO) {
        String responseString;
        Talent talent = talentService.findByEmailOrThrow(eventDTO.getTalentEmail());

        //todo
        Interview interview = interviewRepository.findAllByTalent_Id(talent.getId()).stream()
                .filter(s -> s.getInterviewStatus() == InterviewStatus.PREPARED)
                .findFirst().orElseThrow(() -> new RuntimeException("Not found prepared interview for this talent"));

        if (eventDTO.getEventType().equals(EventType.CREATED)) {
            interview.setStartDate(eventDTO.getStartDate());
            interview.setEndDate(eventDTO.getEndDate());
            interview.setInterviewStatus(InterviewStatus.SCHEDULED);
            responseString = selectedResponse;
        } else {
            sendRejectionEmailUsers(interview);
            interview.setInterviewStatus(InterviewStatus.IN_PREPARATION);
            responseString = rejectedResponse;
        }
        interviewRepository.save(interview);
        log.info("save  addEvent");

        return responseString;
    }

    private void sendRejectionEmailUsers(Interview interview) {
        interview.getUsers().forEach(u -> {
            String emailText = mailTextGenerator.getEmailText(null, u, EmailTextType.TO_USER, null);
            sendEmail(u.getEmail(), EmailTextGenerator.getSubject(), emailText);
        });
        log.info("finished send email Users ");
    }

    @Override
    public List<Interview> findTalentAllInterviews(Long id) {
        talentService.findByIdOrThrow(id);
        return interviewRepository.findAllByTalent_Id(id);
    }
}

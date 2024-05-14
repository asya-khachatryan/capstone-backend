package edu.aua.interviews.service.impl;

import edu.aua.auth.persistance.User;
import edu.aua.auth.service.UserService;
import edu.aua.common.service.EmailService;
import edu.aua.interviews.converter.InterviewConverter;
import edu.aua.interviews.persistance.EventType;
import edu.aua.interviews.persistance.Interview;
import edu.aua.interviews.persistance.InterviewStatus;
import edu.aua.interviews.persistance.dto.CalendlyEventDTO;
import edu.aua.interviews.persistance.dto.InterviewRequestDTO;
import edu.aua.interviews.persistance.dto.InterviewResponseDTO;
import edu.aua.interviews.repositories.InterviewRepository;
import edu.aua.interviews.service.InterviewService;
import edu.aua.interviews.service.InterviewerService;
import edu.aua.talents.persistance.Talent;
import edu.aua.talents.persistance.TalentStatus;
import edu.aua.talents.persistance.dto.TalentRequestDTO;
import edu.aua.talents.service.TalentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
@PropertySource("classpath:/interview-service.properties")
public class InterviewServiceImpl implements InterviewService {

    private final InterviewRepository interviewRepository;
    private final InterviewConverter interviewConverter;
    private final TalentService talentService;
    private final InterviewerService interviewerService;
    private final EmailService emailService;
    private final UserService userService;

    @Override
    public InterviewResponseDTO startInterviewPreparation(InterviewRequestDTO interviewRequestDTO, String hrManagerUsername) {
        User hrManager = userService.findByUsernameOrThrow(hrManagerUsername);
        Interview interview = new Interview();
        Talent talent = talentService.findByIdOrThrow(interviewRequestDTO.getTalentID());
        interview.setTalent(talent);
        interview.setInterviewers(interviewRequestDTO.getInterviewerIds().stream().map(interviewerService::findByIdOrThrow).toList());
        interview.setInterviewStatus(InterviewStatus.IN_PREPARATION);
        interview.setInterviewType(interviewRequestDTO.getInterviewType());
//        String emailText = generateInterviewInvitationEmailText(
//                interview.getTalent().getFullName(),
//                interview.getTalent().getSpecialization().getSpecializationType(),
//                //todo check
//                interview.getUrl().toString(),
//                hrManager.getEmail(),
//                hrManager.getFullName());
//        EmailDTO emailDTO = new EmailDTO(interview.getTalent().getEmail(), INTERVIEW_INVITATION_SUBJECT, emailText);
//        emailService.sendEmail(emailDTO);
        interviewRepository.save(interview);
        talent.setTalentStatus(TalentStatus.INTERVIEW_PREPARATION);
        talentService.updateStatus(new TalentRequestDTO(talent.getName(),
                talent.getSurname(), talent.getEmail(), talent.getPhoneNumber(),
                talent.getSpecialization().getId(), talent.getTalentStatus()));
        return interviewConverter.convertToDTO(interview);
    }

    @Override
    public Boolean addEvent(CalendlyEventDTO eventDTO) {
        Talent talent = talentService.findByEmailOrThrow(eventDTO.getTalentEmail());
        Interview interview = interviewRepository
                .findAllByTalentEmailAndInterviewStatus(talent.getEmail(), InterviewStatus.IN_PREPARATION)
                .get(0);
        if (eventDTO.getEventType().equals(EventType.CREATED)) {
            interview.setStartDate(eventDTO.getStartDate());
            interview.setEndDate(eventDTO.getEndDate());
            interview.setInterviewStatus(InterviewStatus.SCHEDULED);
        } else {
//            sendRejectionEmailUsers(interview);
            interview.setInterviewStatus(InterviewStatus.IN_PREPARATION);
        }
        interviewRepository.save(interview);
        return true;
    }

//    private void sendRejectionEmailUsers(Interview interview) {
//        interview.getInterviewers().forEach(u -> {
//            String emailText = mailTextGenerator.getEmailText(null, u, EmailTextType.TO_USER, null);
//            sendEmail(u.getEmail(), EmailTextGenerator.getSubject(), emailText);
//        });
//        log.info("finished send email Users ");
//    }

    @Override
    public List<Interview> findTalentAllInterviews(Long id) {
        talentService.findByIdOrThrow(id);
        return interviewRepository.findAllByTalent_Id(id);
    }

}

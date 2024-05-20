package edu.aua.interviews.service.impl;

import edu.aua.auth.persistance.User;
import edu.aua.auth.service.UserService;
import edu.aua.common.exception.NotFoundException;
import edu.aua.common.model.EmailDTO;
import edu.aua.common.service.EmailService;
import edu.aua.interviews.converter.InterviewConverter;
import edu.aua.interviews.persistance.EventType;
import edu.aua.interviews.persistance.Interview;
import edu.aua.interviews.persistance.InterviewStatus;
import edu.aua.interviews.persistance.Interviewer;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import static edu.aua.interviews.service.EmailTextGenerator.INTERVIEW_CONFIRMATION_SUBJECT;
import static edu.aua.interviews.service.EmailTextGenerator.INTERVIEW_INVITATION_SUBJECT;
import static edu.aua.interviews.service.EmailTextGenerator.INTERVIEW_RESCHEDULING_SUBJECT;
import static edu.aua.interviews.service.EmailTextGenerator.generateInterviewConfirmationEmailText;
import static edu.aua.interviews.service.EmailTextGenerator.generateInterviewInvitationEmailText;
import static edu.aua.interviews.service.EmailTextGenerator.generateInterviewRescheduleEmailText;


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

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    @Value("${calendly.meeting.uri}")
    private String calendlyUri;

    @Override
    public List<Interview> findAll() {
        return interviewRepository.findAll();
    }

    @Override
    @Transactional
    public InterviewResponseDTO startInterviewPreparation(InterviewRequestDTO interviewRequestDTO, String hrManagerUsername) {
        User hrManager = userService.findByUsernameOrThrow(hrManagerUsername);
        Interview interview = new Interview();
        Talent talent = talentService.findByIdOrThrow(interviewRequestDTO.getTalentID());
        interview.setTalent(talent);
        interview.setInterviewers(interviewRequestDTO.getInterviewerIds().stream().map(interviewerService::findByIdOrThrow).toList());
        interview.setInterviewStatus(InterviewStatus.IN_PREPARATION);
        interview.setInterviewType(interviewRequestDTO.getInterviewType());
        interview.setUrl(URI.create(interviewRequestDTO.getCalendarURI()));
        String emailText = generateInterviewInvitationEmailText(
                interview.getTalent().getFullName(),
                interview.getTalent().getSpecialization().getSpecializationName(),
                interview.getUrl().toString(),
                hrManager.getEmail(),
                hrManager.getFullName());
        EmailDTO emailDTO = new EmailDTO(interview.getTalent().getEmail(), INTERVIEW_INVITATION_SUBJECT, emailText);
        emailService.sendEmail(emailDTO);
        interviewRepository.save(interview);
        talent.setTalentStatus(TalentStatus.INTERVIEW_PREPARATION);
        talentService.updateStatus(talent.getId(), new TalentRequestDTO(talent.getName(),
                talent.getSurname(), talent.getEmail(), talent.getPhoneNumber(),
                talent.getSpecialization().getId(), talent.getTalentStatus()));
        return interviewConverter.convertToDTO(interview);
    }

    @Override
    public Boolean addEvent(CalendlyEventDTO eventDTO) {
        Talent talent = talentService.findLatestByEmailOrThrow(eventDTO.getTalentEmail());
        List<Interview> interviews = interviewRepository
                .findAllByTalentEmail(talent.getEmail());
        Interview interview;
        if (eventDTO.getEventType().equals(EventType.CREATED)) {
            interview = interviews.stream().filter(a -> a.getInterviewStatus().equals(InterviewStatus.IN_PREPARATION)).findFirst()
                    .orElseThrow(() -> new NotFoundException("Interview not found"));
            interview.setStartDate(eventDTO.getStartDate());
            interview.setEndDate(eventDTO.getEndDate());
            String emailText = generateInterviewConfirmationEmailText(
                    interview.getTalent().getFullName(),
                    interview.getTalent().getSpecialization().getSpecializationName(),
                    interview.getStartDate().format(formatter),
                    formatDuration(interview.getStartDate(), interview.getEndDate()),
                    formatInterviewers(interview.getInterviewers())
            );
            EmailDTO emailDTO = new EmailDTO(interview.getTalent().getEmail(),
                    INTERVIEW_CONFIRMATION_SUBJECT, emailText,
                    interview.getInterviewers().stream().map(Interviewer::getEmail).toList());
            emailService.sendEmail(emailDTO);
            interview.setInterviewStatus(InterviewStatus.SCHEDULED);
        } else {
            interview = interviews.stream().filter(a -> a.getInterviewStatus().equals(InterviewStatus.SCHEDULED)).findFirst()
                    .orElseThrow(() -> new NotFoundException("Interview not found"));
            sendRescheduleEmail(interview);
            interview.setInterviewStatus(InterviewStatus.IN_PREPARATION);
            interview.setStartDate(null);
            interview.setEndDate(null);
        }
        interviewRepository.save(interview);
        return true;
    }

    private String formatInterviewers(List<Interviewer> interviewers) {
        return interviewers.stream().map(a -> (a.getFullName() + ", " + a.getPosition()))
                .collect(Collectors.joining(", "));
    }

    private void sendRescheduleEmail(Interview interview) {
        String emailText = generateInterviewRescheduleEmailText(
                interview.getTalent().getFullName(),
                interview.getTalent().getSpecialization().getSpecializationName(),
                calendlyUri);
        EmailDTO emailDTO = new EmailDTO(interview.getTalent().getEmail(),
                INTERVIEW_RESCHEDULING_SUBJECT, emailText,
                interview.getInterviewers().stream().map(Interviewer::getEmail).toList());
        emailService.sendEmail(emailDTO);
    }

    public static String formatDuration(LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        Duration duration = Duration.between(fromDateTime, toDateTime);
        long totalMinutes = duration.toMinutes();
        long hours = totalMinutes / 60;
        long minutes = totalMinutes % 60;

        return String.format("%dh %dmin", hours, minutes);
    }

    @Override
    public List<Interview> findTalentAllInterviews(Long id) {
        talentService.findByIdOrThrow(id);
        return interviewRepository.findAllByTalent_Id(id);
    }

    @Override
    public Interview findByIdOrThrow(Long id) {
        return interviewRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("No interview found by this id %s", id)));
    }

    @Override
    public Interview submitFeedback(Long id, String feedback, String username) {
        Interview interview = findByIdOrThrow(id);
        interview.setInterviewFeedback(feedback);
        return interviewRepository.save(interview);
    }
}

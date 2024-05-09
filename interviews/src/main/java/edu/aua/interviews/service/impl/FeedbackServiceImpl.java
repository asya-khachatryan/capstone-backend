package edu.aua.interviews.service.impl;

import edu.aua.common.exception.NotFoundException;
import edu.aua.interviews.converter.FeedbackConverter;
import edu.aua.interviews.persistance.Interview;
import edu.aua.interviews.persistance.InterviewFeedback;
import edu.aua.interviews.persistance.InterviewStatus;
import edu.aua.interviews.persistance.InterviewType;
import edu.aua.interviews.persistance.dto.InterviewFeedbackDTO;
import edu.aua.interviews.repositories.FeedbackRepository;
import edu.aua.interviews.repositories.InterviewRepository;
import edu.aua.interviews.service.FeedbackService;
import edu.aua.talents.converter.TalentConverter;
import edu.aua.talents.persistance.entity.Talent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final FeedbackConverter feedbackConverter;
    private final InterviewRepository interviewRepository;
    private final TalentConverter talentConverter;

    @Value("${interview.flow.passing.threshold}")
    private Integer passingThreshold;

    @Override
    public InterviewFeedbackDTO create(InterviewFeedbackDTO feedbackRequestDTO, Long interviewID) {
        Interview interview = interviewRepository.findById(interviewID)
                .orElseThrow(() -> new NotFoundException("No interview found by this id", interviewID));
        InterviewFeedback entity = feedbackConverter.convertToEntity(feedbackRequestDTO);
        Talent talent = interview.getTalent();

        //todo
        InterviewType interviewType = interview.getInterviewType();
        switch (interviewType) {
            case HR:
                interview.setInterviewStatus(InterviewStatus.WAITING_STAGE);
                interview.setInterviewStatus(InterviewStatus.CLOSED);
                entity.setInterview(interview);
                break;

            case TECHNICAL:
                passedTechInterview(interview, entity);
                techInterviewRejection(interview, entity, talent);
                break;
        }
        feedbackRepository.save(entity);
        interviewRepository.save(interview);
        return feedbackConverter.convertToDTO(entity);
    }

    private void passedTechInterview(Interview interview, InterviewFeedback entity) {
        interview.setInterviewStatus(InterviewStatus.WAITING_STAGE);
        interview.setInterviewFeedback(entity);
        entity.setInterview(interview);
        feedbackRepository.save(entity);
        interviewRepository.save(interview);
    }

    private void techInterviewRejection(Interview interview, InterviewFeedback entity, Talent talent) {
        interview.setInterviewFeedback(entity);
//        talent.getInterviews().stream()
//                .filter(t -> t.getInterviewStatus() == InterviewStatus.WAITING_STAGE)
//                .forEach(i -> i.setInterviewStatus(InterviewStatus.FINISHED));
        interview.setInterviewStatus(InterviewStatus.CLOSED);
        entity.setInterview(interview);
    }
}

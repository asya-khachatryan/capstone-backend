package edu.aua.interviews.converter.impl;

import edu.aua.interviews.converter.FeedbackConverter;
import edu.aua.interviews.converter.InterviewConverter;
import edu.aua.interviews.persistance.InterviewFeedback;
import edu.aua.interviews.persistance.dto.InterviewFeedbackDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FeedbackConverterImpl implements FeedbackConverter {

    @Autowired
    private InterviewConverter interviewConverter;

    @Override
    public List<InterviewFeedbackDTO> bulkConvertToDTO(List<InterviewFeedback> interviewFeedbacks) {
        return interviewFeedbacks.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public InterviewFeedbackDTO convertToDTO(InterviewFeedback interviewFeedback) {
        final InterviewFeedbackDTO feedbackDTO = new InterviewFeedbackDTO();
        feedbackDTO.setId(interviewFeedback.getId());
        feedbackDTO.setTopic(interviewFeedback.getTopic());
        feedbackDTO.setFeedback(interviewFeedback.getFeedback());
        feedbackDTO.setScore(interviewFeedback.getScore());
        return feedbackDTO;

    }

    @Override
    public List<InterviewFeedback> bulkConvertToEntity(List<InterviewFeedbackDTO> interviewFeedbacks) {
        return interviewFeedbacks.stream().map(this::convertToEntity).collect(Collectors.toList());

    }

    @Override
    public InterviewFeedback convertToEntity(InterviewFeedbackDTO interviewFeedbackReqDTO) {
        if (interviewFeedbackReqDTO == null) {
            return null;
        }
        final InterviewFeedback feedback = new InterviewFeedback();
        feedback.setTopic(interviewFeedbackReqDTO.getTopic());
        feedback.setFeedback(interviewFeedbackReqDTO.getFeedback());
        feedback.setScore(interviewFeedbackReqDTO.getScore());

        return feedback;
    }
}

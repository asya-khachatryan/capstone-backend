package edu.aua.interviews.service;

import edu.aua.interviews.persistance.enums.InterviewType;
import edu.aua.interviews.service.dto.InterviewFeedbackRequestDTO;
import edu.aua.interviews.service.dto.InterviewFeedbackResponseDTO;

public interface FeedbackService {

    InterviewFeedbackResponseDTO create(InterviewFeedbackRequestDTO feedbackRequestDTO, InterviewType interviewType, Long talentId);

}

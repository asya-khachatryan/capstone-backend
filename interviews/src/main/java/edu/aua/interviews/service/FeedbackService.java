package edu.aua.interviews.service;

import edu.aua.interviews.persistance.InterviewType;
import edu.aua.interviews.persistance.dto.InterviewFeedbackDTO;

public interface FeedbackService {
    InterviewFeedbackDTO create(InterviewFeedbackDTO feedbackRequestDTO, InterviewType interviewType, Long talentId);
}

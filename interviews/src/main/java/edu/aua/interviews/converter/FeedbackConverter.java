package edu.aua.interviews.converter;

import edu.aua.interviews.persistance.InterviewFeedback;
import edu.aua.interviews.persistance.dto.InterviewFeedbackDTO;


import java.util.List;

public interface FeedbackConverter {

    List<InterviewFeedbackDTO> bulkConvertToDTO(List<InterviewFeedback> interviewFeedbacks);

    InterviewFeedbackDTO convertToDTO(InterviewFeedback interviewFeedback);

    List<InterviewFeedback> bulkConvertToEntity(List<InterviewFeedbackDTO> interviewFeedbacks);

    InterviewFeedback convertToEntity(InterviewFeedbackDTO interviewFeedbackReqDTO);
}

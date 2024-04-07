package edu.aua.interviews.converter;

import edu.aua.interviews.persistance.entity.interview.InterviewFeedback;
import edu.aua.interviews.service.dto.InterviewFeedbackRequestDTO;
import edu.aua.interviews.service.dto.InterviewFeedbackResponseDTO;


import java.util.List;

public interface FeedbackConverter {

    List<InterviewFeedbackResponseDTO> bulkConvertToDTO(List<InterviewFeedback> interviewFeedbacks);

    InterviewFeedbackResponseDTO convertToDTO(InterviewFeedback interviewFeedback);

    List<InterviewFeedback> bulkConvertToEntity(List<InterviewFeedbackRequestDTO> interviewFeedbacks);

    InterviewFeedback convertToEntity(InterviewFeedbackRequestDTO interviewFeedbackReqDTO);
}

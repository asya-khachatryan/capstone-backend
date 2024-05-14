package edu.aua.interviews.converter.impl;

import edu.aua.interviews.converter.FeedbackConverter;
import edu.aua.interviews.converter.InterviewConverter;
import edu.aua.interviews.converter.InterviewerConverter;
import edu.aua.interviews.persistance.Interview;
import edu.aua.interviews.persistance.InterviewStatus;
import edu.aua.interviews.persistance.InterviewType;
import edu.aua.interviews.persistance.dto.InterviewRequestDTO;
import edu.aua.interviews.persistance.dto.InterviewResponseDTO;
import edu.aua.interviews.repositories.InterviewerRepository;
import edu.aua.talents.converter.TalentConverter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class InterviewConverterImpl implements InterviewConverter {

    @Autowired
    private InterviewerConverter interviewerConverter;

    @Autowired
    private FeedbackConverter feedbackConverter;

    private final InterviewerRepository interviewerRepository;
    private final TalentConverter talentConverter;

    @Override
    public List<InterviewResponseDTO> bulkConvertToDTO(List<Interview> interviews) {
        return interviews.stream().map(this::convertToDTO).collect(Collectors.toList());

    }

    @Override
    public InterviewResponseDTO convertToDTO(Interview interview) {
        if (interview == null) {
            return null;
        }
        final InterviewResponseDTO interviewResponseDTO = new InterviewResponseDTO();
        interviewResponseDTO.setId(interview.getId());
        interviewResponseDTO.setStartDate(interview.getStartDate());
        interviewResponseDTO.setEndDate(interview.getEndDate());
        interviewResponseDTO.setInterviewType(InterviewType.valueOf(interview.getInterviewType().name()));
        interviewResponseDTO.setInterviewStatus(InterviewStatus.valueOf(interview.getInterviewStatus().name()));
        interviewResponseDTO.setTalentDTO(talentConverter.convertToDTO(interview.getTalent()));
        interviewResponseDTO.setInterviewerDTO(interviewerConverter.bulkConvertToDTO(interview.getInterviewers()));
        if(interview.getInterviewFeedback() != null) {
            interviewResponseDTO.setInterviewFeedback(feedbackConverter.convertToDTO(interview.getInterviewFeedback()));
        }
        return interviewResponseDTO;

    }

    @Override
    public List<Interview> bulkConvertToEntity(List<InterviewRequestDTO> interviews) {
        return interviews.stream().map(this::convertToEntity).collect(Collectors.toList());
    }

    @Override
    public Interview convertToEntity(InterviewRequestDTO interviewRequestDTO) {
        final Interview interview = new Interview();
        interview.setUrl(URI.create(interviewRequestDTO.getCalendarURI()));
//        interview.setTalent(talentConverter.convertToEntity(interviewRequestDTO.getTalentDTO()));
        interview.setInterviewers(interviewerRepository.findAllById(interviewRequestDTO.getInterviewerIds()));
        return interview;
    }
}

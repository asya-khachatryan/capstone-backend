package edu.aua.interviews.controller;

import edu.aua.interviews.persistance.dto.InterviewFeedbackDTO;
import edu.aua.interviews.service.FeedbackService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feedback")
@Slf4j
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<InterviewFeedbackDTO> create(
            @RequestBody @Valid InterviewFeedbackDTO feedbackRequestDTO,
            @RequestParam Long interviewID) {
        return ResponseEntity.ok(feedbackService.create(feedbackRequestDTO, interviewID));
    }
}

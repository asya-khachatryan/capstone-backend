package edu.aua.interviews.controller;

import edu.aua.interviews.persistance.Interview;
import edu.aua.interviews.persistance.dto.CalendlyEventDTO;
import edu.aua.interviews.persistance.dto.InterviewRequestDTO;
import edu.aua.interviews.persistance.dto.InterviewResponseDTO;
import edu.aua.interviews.service.InterviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/interview")
public class InterviewController {

    private final InterviewService interviewService;

    @PostMapping
    public ResponseEntity<InterviewResponseDTO> create(@RequestBody @Valid InterviewRequestDTO interviewRequestDTO) {
        return ResponseEntity.ok(interviewService.startInterviewPreparation(interviewRequestDTO));
    }

    @PutMapping
    public ResponseEntity<String> updateFromEvent(@RequestBody @Valid CalendlyEventDTO eventDTO) {
        return ResponseEntity.ok(interviewService.addEvent(eventDTO));
    }

    @GetMapping("/search/{talentId}")
    public ResponseEntity<List<Interview>> findAllInterviews(@PathVariable Long talentId) {
        return ResponseEntity.ok(interviewService.findTalentAllInterviews(talentId));
    }
}

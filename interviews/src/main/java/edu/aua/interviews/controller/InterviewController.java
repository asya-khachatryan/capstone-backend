package edu.aua.interviews.controller;

import edu.aua.interviews.persistance.entity.interview.Interview;
import edu.aua.interviews.service.InterviewService;
import edu.aua.interviews.service.dto.InterviewEventDTO;
import edu.aua.interviews.service.dto.InterviewRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/interview")
public class InterviewController {

    private final InterviewService interviewService;

    @PostMapping
    public @ResponseBody String create(@RequestBody @Valid InterviewRequestDTO interviewRequestDTO) {
        return interviewService.preparationInterview(interviewRequestDTO);

    }

    @PutMapping
    public @ResponseBody String updateFromEvent(@RequestBody @Valid InterviewEventDTO eventDTO) {
        return interviewService.addEvent(eventDTO);
    }

    @GetMapping("/search/{talentId}")
    public List<Interview> findAllInterviews(@PathVariable Long talentId) {
        log.info("In InterviewController, /interview/search/{talentId},search");
        return interviewService.findTalentAllInterviews(talentId);
    }

}

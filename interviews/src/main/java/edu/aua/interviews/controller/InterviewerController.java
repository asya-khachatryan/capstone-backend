package edu.aua.interviews.controller;

import edu.aua.interviews.converter.InterviewerConverter;
import edu.aua.interviews.persistance.dto.InterviewerDTO;
import edu.aua.interviews.service.InterviewerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/interviewer")
public class InterviewerController {
    private final InterviewerService service;
    private final InterviewerConverter converter;

    @GetMapping("/{id}")
    public ResponseEntity<InterviewerDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(converter.convertToDTO(service.findByIdOrThrow(id)));
    }

    @PostMapping
    public ResponseEntity<InterviewerDTO> create(@RequestBody @Valid InterviewerDTO interviewerDTO) {
        return ResponseEntity.ok(converter.convertToDTO(service.create(interviewerDTO)));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<InterviewerDTO> update(@PathVariable Long id, @RequestBody @Valid InterviewerDTO interviewerDTO) {
        return ResponseEntity.ok(converter.convertToDTO(service.update(id, interviewerDTO)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteById(id));
    }
}

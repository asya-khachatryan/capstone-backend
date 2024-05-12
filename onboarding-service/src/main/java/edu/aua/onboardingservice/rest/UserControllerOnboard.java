package edu.aua.onboardingservice.rest;

import edu.aua.onboardingservice.facade.UserFacade;
import edu.aua.onboardingservice.service.dto.MenteeDto;
import edu.aua.onboardingservice.service.dto.MentorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin
public class UserControllerOnboard {

    private final UserFacade userFacade;

    public UserControllerOnboard(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @PostMapping("/mentee")
    public ResponseEntity<MenteeDto> createMentee(@RequestBody MenteeDto menteeDto) {
        return ResponseEntity.ok(this.userFacade.createMentee(menteeDto));
    }

    @GetMapping("/mentor")
    public ResponseEntity<List<MentorDto>> getAll() {
        return ResponseEntity.ok(this.userFacade.getAllMentors());
    }

    @GetMapping("/mentee")
    public ResponseEntity<List<MenteeDto>> getAllMentees() {
        return ResponseEntity.ok(this.userFacade.getAllMentees());
    }

    @GetMapping("/mentee/{id}")
    public ResponseEntity<MenteeDto> findMenteeById(@PathVariable Long id) {
        return ResponseEntity.ok(this.userFacade.findMenteeById(id));
    }

    @GetMapping("/mentor/{id}")
    public ResponseEntity<MentorDto> findMentorById(@PathVariable Long id) {
        return ResponseEntity.ok(this.userFacade.findMentorById(id));
    }

    @DeleteMapping("mentee/{id}")
    public void deleteMentee(@PathVariable Long id) {
        this.userFacade.deleteMentee(id);
    }

    @DeleteMapping("mentor/{id}")
    public void deleteMentor(@PathVariable Long id) {
        this.userFacade.deleteMentor(id);
    }
}

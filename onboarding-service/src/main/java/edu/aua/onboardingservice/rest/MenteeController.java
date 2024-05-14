package edu.aua.onboardingservice.rest;

import edu.aua.onboardingservice.converter.MenteeConverter;
import edu.aua.onboardingservice.persistance.MenteeDto;
import edu.aua.onboardingservice.service.MenteeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/mentee")
@RequiredArgsConstructor
public class MenteeController {

    private final MenteeService service;
    private final MenteeConverter converter;

    @GetMapping
    public ResponseEntity<List<MenteeDto>> getAllMentees() {
        return ResponseEntity.ok(converter.bulkConvertToDto(service.findALl()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenteeDto> findMenteeById(@PathVariable Long id) {
        return ResponseEntity.ok(converter.convertToDto(service.findById(id)));
    }

    @PostMapping("/{menteeId}/onboarding/email")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Boolean> sendOnboardingDocument(@PathVariable Long menteeId,
                                                          @RequestBody String documentUrl,
                                                          @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(service.sendOnboardingEmail(menteeId, userDetails.getUsername(), documentUrl));
    }

    @PostMapping
    public ResponseEntity<MenteeDto> create(@RequestBody @Valid MenteeDto menteeDto) {
        return ResponseEntity.ok(converter.convertToDto(service.create(menteeDto)));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MenteeDto> update(@PathVariable Long id, @RequestBody @Valid MenteeDto menteeDto) {
        return ResponseEntity.ok(converter.convertToDto(service.update(id, menteeDto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteById(id));
    }

//    @GetMapping("/search")
//    public ResponseEntity<List<MenteeDto>> search(@RequestParam String query, @RequestParam String type) {
//        return ResponseEntity.ok(converter.bulkConvertToDto(service.searchMentee(query, type)));
//    }
}

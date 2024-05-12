package edu.aua.onboardingservice.rest;

import edu.aua.onboardingservice.service.MenteeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/mentee")
@RequiredArgsConstructor
public class MenteeController {

    private final MenteeService menteeService;

    @PostMapping("/{menteeId}/onboarding/email")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Boolean> sendOnboardingDocument(@PathVariable Long menteeId,
                                                          @RequestBody String documentUrl,
                                                          @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(menteeService.sendOnboardingEmail(menteeId, userDetails.getUsername(), documentUrl));
    }
}

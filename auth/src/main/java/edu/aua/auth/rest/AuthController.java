package edu.aua.auth.rest;

import edu.aua.auth.dto.AuthRequest;
import edu.aua.auth.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class AuthController {

    @Value("${jwt.cookieExpiry}")
    private int cookieExpiry;

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> authenticateUser(@RequestBody final AuthRequest loginRequest,
                                                   HttpServletResponse response) {
        String accessToken = authService.login(loginRequest);
        ResponseCookie cookie = ResponseCookie.from("accessToken", accessToken)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(cookieExpiry)
                .sameSite("None")
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        return ResponseEntity.ok().build();
    }
}

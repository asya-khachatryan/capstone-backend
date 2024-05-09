package edu.aua.auth.service;


import edu.aua.auth.dto.AuthRequest;

public interface AuthService {
    String login(final AuthRequest loginRequest);
}

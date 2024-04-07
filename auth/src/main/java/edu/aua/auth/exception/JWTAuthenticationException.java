package edu.aua.auth.exception;

import javax.naming.AuthenticationException;

public class JWTAuthenticationException extends AuthenticationException {

    public JWTAuthenticationException(String explanation) {
        super(explanation);
    }

    public JWTAuthenticationException() {
    }
}

package edu.aua.onboardingservice.exception;

import edu.aua.onboardingservice.validation.ValidationError;

public class ViolationException extends RuntimeException {

    private final ValidationError validationError;

    public ViolationException(ValidationError validationError) {
        this.validationError = validationError;
    }

    public ValidationError getValidationError() {
        return validationError;
    }
}

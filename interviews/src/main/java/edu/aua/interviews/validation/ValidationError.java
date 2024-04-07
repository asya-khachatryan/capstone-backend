package edu.aua.interviews.validation;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;

public class ValidationError {

    @Getter
    private final Collection<Violation> violations;

    public ValidationError() {
        this.violations = new ArrayList<>();
    }

    public void addViolation(String field, String message) {
        this.violations.add(new Violation(field, message));
    }

    public boolean hasViolation() {
        return !this.violations.isEmpty();
    }
}

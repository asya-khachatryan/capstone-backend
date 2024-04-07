package edu.aua.onboardingservice.exception;

public class MentorNotFoundException extends RuntimeException {
    private final String message;

    private final Object data;

    public MentorNotFoundException(String message, Object data) {
        super(message);
        this.message = message;
        this.data = data;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}

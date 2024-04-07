package edu.aua.onboardingservice.exception;

public class RoadmapNotFoundException extends RuntimeException {
    private final String message;

    private final Object data;

    public RoadmapNotFoundException(String message, Object data) {
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

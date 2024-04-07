package edu.aua.talents.exception;

public class TalentNotFoundException extends RuntimeException {
    private final String message;

    private final Object data;

    public TalentNotFoundException(String message, Object data) {
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

package edu.aua.interviews.excaption;

import lombok.Getter;

public class EmailNotFoundException extends RuntimeException {
    @Getter
    private final String message;

    @Getter
    private final Object data;

    public EmailNotFoundException(String message, Object data) {
        super(message);
        this.message = message;
        this.data = data;
    }
}

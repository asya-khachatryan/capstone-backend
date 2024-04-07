package edu.aua.interviews.validation;

import lombok.Data;

@Data
public class Violation {

    private final String field;

    private final String message;

}
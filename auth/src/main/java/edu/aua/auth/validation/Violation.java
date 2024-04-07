package edu.aua.auth.validation;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Violation {

    private final String field;

    private final String message;

}


package edu.aua.common.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseError {

    private int status;
    private Date timestamp;
    private String message;
    private String description;
}


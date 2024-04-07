package edu.aua.auth.exeptionhandler;

import edu.aua.auth.exception.ResourceNotFoundException;
import edu.aua.auth.exception.ViolationException;
import edu.aua.auth.validation.ErrorCollector;
import edu.aua.auth.validation.ValidationError;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final ErrorCollector errorCollector;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ResponseError handleBindException(BindException e) {
        ValidationError error = new ValidationError();

        for (ObjectError oe : e.getBindingResult().getAllErrors()) {
            error.addViolation(((FieldError) oe).getField(), oe.getDefaultMessage());
        }

        return ResponseError
                .builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(errorCollector.defaultMessagesAsJson(error))
                .data(error)
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ViolationException.class)
    @ResponseBody
    public ResponseError handleViolationException(ViolationException e) {
        ValidationError error = e.getValidationError();

        return ResponseError
                .builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(errorCollector.defaultMessagesAsJson(error))
                .data(error)
                .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public ResponseError handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseError
                .builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(e.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    public ResponseError handleResourceNotFoundException(ResourceNotFoundException e) {
        return ResponseError
                .builder()
                .code(ErrorCodes.ERR_CODE_USERNAME_NOT_FOUND)
                .message(e.getMessage())
                .data(e.getData())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseBody
    public ResponseError handleBadCredentialsException(BadCredentialsException e) {
        return ResponseError
                .builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseError handleRuntimeException(RuntimeException e) {
        return ResponseError
                .builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(e.getMessage())
                .build();
    }


}
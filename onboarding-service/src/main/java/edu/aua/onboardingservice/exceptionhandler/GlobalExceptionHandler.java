package edu.aua.onboardingservice.exceptionhandler;//package com.disqo.onboarding_flow_service.exceptionhandler;
//
//import com.disqo.onboarding_flow_service.exception.*;
//import com.disqo.onboarding_flow_service.validation.ErrorCollector;
//import com.disqo.onboarding_flow_service.validation.ValidationError;
//import org.springframework.http.HttpStatus;
//import org.springframework.validation.BindException;
//import org.springframework.validation.FieldError;
//import org.springframework.validation.ObjectError;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.ResponseStatus;
//
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//    private final ErrorCollector errorCollector;
//
//    public GlobalExceptionHandler(ErrorCollector errorCollector) {
//        this.errorCollector = errorCollector;
//    }
//
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(BindException.class)
//    @ResponseBody
//    public ResponseError handleBindException(BindException e) {
//        ValidationError error = new ValidationError();
//
//        for (ObjectError oe : e.getBindingResult().getAllErrors()) {
//            error.addViolation(((FieldError) oe).getField(), oe.getDefaultMessage());
//        }
//
//        return ResponseError
//                .builder()
//                .code(HttpStatus.BAD_REQUEST.value())
//                .message(errorCollector.defaultMessagesAsJson(error))
//                .data(error)
//                .build();
//    }
//
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(ViolationException.class)
//    @ResponseBody
//    public ResponseError handleViolationException(ViolationException e) {
//        ValidationError error = e.getValidationError();
//
//        return ResponseError
//                .builder()
//                .code(HttpStatus.BAD_REQUEST.value())
//                .message(errorCollector.defaultMessagesAsJson(error))
//                .data(error)
//                .build();
//    }
//
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(IllegalArgumentException.class)
//    @ResponseBody
//    public ResponseError handleIllegalArgumentException(IllegalArgumentException e) {
//        return ResponseError
//                .builder()
//                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
//                .message(e.getMessage())
//                .build();
//    }
//
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(RoadmapNotFoundException.class)
//    @ResponseBody
//    public ResponseError handleRoadmapNotFoundException(RoadmapNotFoundException e) {
//        return ResponseError
//                .builder()
//                .code(ErrorCodes.ERR_CODE_ROADMAP_NOT_FOUND)
//                .message(e.getMessage())
//                .data(e.getData())
//                .build();
//    }
//
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MenteeNotFoundException.class)
//    @ResponseBody
//    public ResponseError handleMenteeNotFoundException(MenteeNotFoundException e) {
//        return ResponseError
//                .builder()
//                .code(ErrorCodes.ERR_CODE_MENTEE_NOT_FOUND)
//                .message(e.getMessage())
//                .data(e.getData())
//                .build();
//    }
//
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MentorNotFoundException.class)
//    @ResponseBody
//    public ResponseError handleMentorNotFoundException(MentorNotFoundException e) {
//        return ResponseError
//                .builder()
//                .code(ErrorCodes.ERR_CODE_MENTOR_NOT_FOUND)
//                .message(e.getMessage())
//                .data(e.getData())
//                .build();
//    }
//
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(SprintNotFoundException.class)
//    @ResponseBody
//    public ResponseError handleSprintNotFoundException(SprintNotFoundException e) {
//        return ResponseError
//                .builder()
//                .code(ErrorCodes.ERR_CODE_SPRINT_NOT_FOUND)
//                .message(e.getMessage())
//                .data(e.getData())
//                .build();
//    }
//
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(RuntimeException.class)
//    @ResponseBody
//    public ResponseError handleRuntimeException(RuntimeException e) {
//        return ResponseError
//                .builder()
//                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
//                .message(e.getMessage())
//                .build();
//    }
//}

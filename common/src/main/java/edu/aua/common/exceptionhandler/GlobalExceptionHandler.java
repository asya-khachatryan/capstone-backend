//package edu.aua.talents.exceptionhandler;
//
//import edu.aua.talents.exception.SpecializationNotFoundException;
//import edu.aua.talents.exception.TalentNotFoundException;
//import edu.aua.talents.exception.ViolationException;
//import edu.aua.talents.validation.ErrorCollector;
//import edu.aua.talents.validation.ValidationError;
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
//    @ExceptionHandler(TalentNotFoundException.class)
//    @ResponseBody
//    public ResponseError handleTalentNotFoundException(TalentNotFoundException e) {
//        return ResponseError
//                .builder()
//                .code(ErrorCodes.ERR_CODE_TALENT_NOT_FOUND)
//                .message(e.getMessage())
//                .data(e.getData())
//                .build();
//    }
//
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(SpecializationNotFoundException.class)
//    @ResponseBody
//    public ResponseError handleSpecializationNotFoundException(SpecializationNotFoundException e) {
//        return ResponseError
//                .builder()
//                .code(ErrorCodes.ERR_CODE_SPECIALIZATION_NOT_FOUND)
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

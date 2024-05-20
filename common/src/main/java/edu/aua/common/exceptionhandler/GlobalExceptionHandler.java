//package edu.aua.common.exceptionhandler;
//
//
//import edu.aua.common.exception.NotFoundException;
//import edu.aua.common.validation.ErrorCollector;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.ResponseStatus;
//
//import java.util.Date;
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
//    //    @ResponseStatus(HttpStatus.BAD_REQUEST)
////    @ExceptionHandler(BindException.class)
////    @ResponseBody
////    public ResponseError handleBindException(BindException e) {
////        ValidationError error = new ValidationError();
////
////        for (ObjectError oe : e.getBindingResult().getAllErrors()) {
////            error.addViolation(((FieldError) oe).getField(), oe.getDefaultMessage());
////        }
////
////        return ResponseError
////                .builder()
////                .code(HttpStatus.BAD_REQUEST.value())
////                .message(errorCollector.defaultMessagesAsJson(error))
////                .data(error)
////                .build();
////    }
////
////    @ResponseStatus(HttpStatus.BAD_REQUEST)
////    @ExceptionHandler(ViolationException.class)
////    @ResponseBody
////    public ResponseError handleViolationException(ViolationException e) {
////        ValidationError error = e.getValidationError();
////
////        return ResponseError
////                .builder()
////                .code(HttpStatus.BAD_REQUEST.value())
////                .message(errorCollector.defaultMessagesAsJson(error))
////                .data(error)
////                .build();
////    }
////
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(IllegalArgumentException.class)
//    @ResponseBody
//    public ResponseError handleIllegalArgumentException(IllegalArgumentException e) {
//        return ResponseError
//                .builder()
//                .timestamp(new Date())
//                .status(HttpStatus.BAD_REQUEST.value())
//                .message(e.getMessage())
//                .build();
//    }
//
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(NotFoundException.class)
//    @ResponseBody
//    public ResponseError handleNotFoundException(NotFoundException e) {
//        return ResponseError
//                .builder()
//                .timestamp(new Date())
//                .status(HttpStatus.NOT_FOUND.value())
//                .message(e.getMessage())
//                .build();
//    }
//
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(RuntimeException.class)
//    @ResponseBody
//    public ResponseError handleRuntimeException(RuntimeException e) {
//        return ResponseError
//                .builder()
//                .timestamp(new Date())
//                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
//                .message(e.getMessage())
//                .build();
//    }
//}

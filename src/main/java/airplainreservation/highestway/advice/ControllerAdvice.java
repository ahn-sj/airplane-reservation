package airplainreservation.highestway.advice;

import airplainreservation.highestway.exception.CustomCommonException;
import airplainreservation.highestway.exception.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(CustomCommonException.class)
    public ResponseEntity<ErrorResponse> handleCustomCommonException(CustomCommonException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        StringBuilder builder = new StringBuilder();

        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            builder.append("[" + fieldError.getField() + "](은)는 " + fieldError.getDefaultMessage());
            builder.append(" 입력된 값: [" + fieldError.getRejectedValue() + "]");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(String.valueOf(builder)));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> exception(Exception e) {
        return ResponseEntity.internalServerError().body(new ErrorResponse(e.getMessage()));
    }
}

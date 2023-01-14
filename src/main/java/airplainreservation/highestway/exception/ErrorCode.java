package airplainreservation.highestway.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    DUPLICATE_EMAIL(400, HttpStatus.BAD_REQUEST, "이미 존재하는 이메일입니다."),
    DUPLICATE_USERNAME(400, HttpStatus.BAD_REQUEST, "이미 존재하는 닉네임입니다.");

    private final int status;
    private final HttpStatus httpStatus;
    private final String message;

    ErrorCode(int status, HttpStatus httpStatus, String message) {
        this.status = status;
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }
}

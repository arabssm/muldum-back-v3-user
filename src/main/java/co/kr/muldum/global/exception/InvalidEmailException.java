package co.kr.muldum.global.exception;

import org.springframework.http.HttpStatus;

public class InvalidEmailException extends BusinessException {

    private static final String ERROR_CODE = "INVALID_EMAIL";

    public InvalidEmailException(String message) {
        super(message, HttpStatus.BAD_REQUEST, ERROR_CODE);
    }

    public InvalidEmailException(String message, Throwable cause) {
        super(message, HttpStatus.BAD_REQUEST, ERROR_CODE, cause);
    }
}

package co.kr.muldum.domain.exception;

import org.springframework.http.HttpStatus;

public class InvalidAuthorizationCodeException extends BusinessException {

    private static final String ERROR_CODE = "INVALID_AUTHORIZATION_CODE";
    private static final HttpStatus HTTP_STATUS = HttpStatus.NOT_FOUND;

    public InvalidAuthorizationCodeException(String message) {
        super(message, HTTP_STATUS, ERROR_CODE);
    }

    public InvalidAuthorizationCodeException(String message, Throwable cause) {
        super(message, HTTP_STATUS, ERROR_CODE, cause);
    }
}
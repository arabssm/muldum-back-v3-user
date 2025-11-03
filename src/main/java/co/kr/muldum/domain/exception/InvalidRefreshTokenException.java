package co.kr.muldum.domain.exception;

import org.springframework.http.HttpStatus;

public class InvalidRefreshTokenException extends BusinessException {

    private static final String ERROR_CODE = "INVALID_REFRESH_TOKEN";

    public InvalidRefreshTokenException(String message) {
        super(message, HttpStatus.UNAUTHORIZED, ERROR_CODE);
    }
}
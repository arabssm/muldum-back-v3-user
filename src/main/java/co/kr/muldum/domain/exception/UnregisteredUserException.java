package co.kr.muldum.domain.exception;

import org.springframework.http.HttpStatus;

public class UnregisteredUserException extends BusinessException {

    private static final String ERROR_CODE = "UNREGISTERED_USER";
    private static final HttpStatus HTTP_STATUS = HttpStatus.CONFLICT;

    public UnregisteredUserException(String email) {
        super("등록되지 않은 사용자입니다: " + email, HTTP_STATUS, ERROR_CODE);
    }
}
package co.kr.muldum.domain.exception;

public class UserValidationException extends DomainValidationException {

    public UserValidationException(String message) {
        super(message);
    }

    public UserValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
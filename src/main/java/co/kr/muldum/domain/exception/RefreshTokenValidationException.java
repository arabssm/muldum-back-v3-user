package co.kr.muldum.domain.exception;

public class RefreshTokenValidationException extends DomainValidationException {

    public RefreshTokenValidationException(String message) {
        super(message);
    }

    public RefreshTokenValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
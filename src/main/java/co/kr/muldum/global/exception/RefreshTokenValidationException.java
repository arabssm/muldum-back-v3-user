package co.kr.muldum.global.exception;

import java.util.Map;

public class RefreshTokenValidationException extends DomainValidationException {

    private static final String ERROR_CODE = "REFRESH_TOKEN_VALIDATION_ERROR";

    public RefreshTokenValidationException(String message) {
        super(ERROR_CODE, message);
    }

    public RefreshTokenValidationException(String message, Map<String, Object> details) {
        super(ERROR_CODE, message, details);
    }

    public RefreshTokenValidationException(String message, Throwable cause) {
        super(ERROR_CODE, message, cause);
    }

    public RefreshTokenValidationException(String message, Map<String, Object> details, Throwable cause) {
        super(ERROR_CODE, message, details, cause);
    }
}

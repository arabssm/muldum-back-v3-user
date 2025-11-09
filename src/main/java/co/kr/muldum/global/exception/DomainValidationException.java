package co.kr.muldum.global.exception;

import org.springframework.http.HttpStatus;

import java.util.Map;

public abstract class DomainValidationException extends GlobalException {

    private static final HttpStatus DEFAULT_STATUS = HttpStatus.BAD_REQUEST;
    private static final String DEFAULT_ERROR_CODE = "DOMAIN_VALIDATION_ERROR";

    protected DomainValidationException(String message) {
        super(message, DEFAULT_STATUS, DEFAULT_ERROR_CODE);
    }

    protected DomainValidationException(String message, Throwable cause) {
        super(message, DEFAULT_STATUS, DEFAULT_ERROR_CODE, cause);
    }

    protected DomainValidationException(String errorCode, String message) {
        super(message, DEFAULT_STATUS, errorCode);
    }

    protected DomainValidationException(String errorCode, String message, Throwable cause) {
        super(message, DEFAULT_STATUS, errorCode, cause);
    }

    protected DomainValidationException(String errorCode, String message, Map<String, Object> details) {
        super(message, DEFAULT_STATUS, errorCode, details);
    }

    protected DomainValidationException(String errorCode,
                                        String message,
                                        Map<String, Object> details,
                                        Throwable cause) {
        super(message, DEFAULT_STATUS, errorCode, details, cause);
    }
}

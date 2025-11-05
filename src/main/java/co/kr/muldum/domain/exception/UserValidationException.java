package co.kr.muldum.domain.exception;

import java.util.Map;

public class UserValidationException extends DomainValidationException {

    private static final String ERROR_CODE = "USER_VALIDATION_ERROR";

    public UserValidationException(String message) {
        super(ERROR_CODE, message);
    }

    public UserValidationException(String message, Map<String, Object> details) {
        super(ERROR_CODE, message, details);
    }

    public UserValidationException(String message, Throwable cause) {
        super(ERROR_CODE, message, cause);
    }

    public UserValidationException(String message, Map<String, Object> details, Throwable cause) {
        super(ERROR_CODE, message, details, cause);
    }
}

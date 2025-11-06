package co.kr.muldum.domain.exception;

import org.springframework.http.HttpStatus;

import java.util.Map;

public abstract class BusinessException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final String errorCode;
    private final Map<String, Object> details;

    protected BusinessException(String message, HttpStatus httpStatus, String errorCode) {
        this(message, httpStatus, errorCode, Map.of(), null);
    }

    protected BusinessException(String message, HttpStatus httpStatus, String errorCode, Throwable cause) {
        this(message, httpStatus, errorCode, Map.of(), cause);
    }

    protected BusinessException(String message, HttpStatus httpStatus, String errorCode, Map<String, Object> details) {
        this(message, httpStatus, errorCode, details, null);
    }

    protected BusinessException(String message,
                                HttpStatus httpStatus,
                                String errorCode,
                                Map<String, Object> details,
                                Throwable cause) {
        super(message, cause);
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.details = details == null ? Map.of() : Map.copyOf(details);
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public Map<String, Object> getDetails() {
        return details;
    }
}

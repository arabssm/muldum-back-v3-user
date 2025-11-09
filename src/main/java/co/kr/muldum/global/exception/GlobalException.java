package co.kr.muldum.global.exception;

import org.springframework.http.HttpStatus;

import java.util.Map;
import java.util.Objects;

/**
 * 애플리케이션 전역에서 일관된 응답 포맷을 제공하기 위해 공통으로 사용하는 예외 기본 클래스입니다.
 */
public abstract class GlobalException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final String errorCode;
    private final Map<String, Object> details;

    protected GlobalException(String message, HttpStatus httpStatus, String errorCode) {
        this(message, httpStatus, errorCode, null, null);
    }

    protected GlobalException(String message, HttpStatus httpStatus, String errorCode, Map<String, Object> details) {
        this(message, httpStatus, errorCode, details, null);
    }

    protected GlobalException(String message, HttpStatus httpStatus, String errorCode, Throwable cause) {
        this(message, httpStatus, errorCode, null, cause);
    }

    protected GlobalException(String message,
                              HttpStatus httpStatus,
                              String errorCode,
                              Map<String, Object> details,
                              Throwable cause) {
        super(message, cause);
        this.httpStatus = Objects.requireNonNull(httpStatus, "httpStatus must not be null.");
        this.errorCode = Objects.requireNonNull(errorCode, "errorCode must not be null.");
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

package co.kr.muldum.domain.exception;

import org.springframework.http.HttpStatus;

import java.util.Map;

public abstract class BusinessException extends GlobalException {

    protected BusinessException(String message, HttpStatus httpStatus, String errorCode) {
        super(message, httpStatus, errorCode);
    }

    protected BusinessException(String message, HttpStatus httpStatus, String errorCode, Throwable cause) {
        super(message, httpStatus, errorCode, cause);
    }

    protected BusinessException(String message,
                                HttpStatus httpStatus,
                                String errorCode,
                                Map<String, Object> details) {
        super(message, httpStatus, errorCode, details);
    }

    protected BusinessException(String message,
                                HttpStatus httpStatus,
                                String errorCode,
                                Map<String, Object> details,
                                Throwable cause) {
        super(message, httpStatus, errorCode, details, cause);
    }
}

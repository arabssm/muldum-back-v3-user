package co.kr.muldum.adapter.in.web.dto;

import lombok.Getter;

import java.util.Map;

@Getter
public class ErrorResponse {

    private final String errorCode;
    private final String message;
    private final Map<String, Object> details;

    private ErrorResponse(String errorCode, String message, Map<String, Object> details) {
        this.errorCode = errorCode;
        this.message = message;
        this.details = details == null ? Map.of() : Map.copyOf(details);
    }

    public static ErrorResponse of(String errorCode, String message) {
        return new ErrorResponse(errorCode, message, null);
    }

    public static ErrorResponse of(String errorCode, String message, Map<String, Object> details) {
        return new ErrorResponse(errorCode, message, details);
    }
}

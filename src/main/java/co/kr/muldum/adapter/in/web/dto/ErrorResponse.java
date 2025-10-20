package co.kr.muldum.adapter.in.web.dto;

import lombok.Getter;

@Getter
public class ErrorResponse {

    private final String errorCode;
    private final String message;

    private ErrorResponse(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public static ErrorResponse of(String errorCode, String message) {
        return new ErrorResponse(errorCode, message);
    }
}
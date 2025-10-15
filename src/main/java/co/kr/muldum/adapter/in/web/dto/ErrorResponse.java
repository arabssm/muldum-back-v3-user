package co.kr.muldum.adapter.in.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private String error;
    private String message;
    private Integer statusCode;

    private ErrorResponse() {
    }

    public static ErrorResponse of(String error, String message) {
        ErrorResponse response = new ErrorResponse();
        response.error = error;
        response.message = message;
        return response;
    }

    public static ErrorResponse of(Integer statusCode, String message) {
        ErrorResponse response = new ErrorResponse();
        response.statusCode = statusCode;
        response.message = message;
        return response;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public Integer getStatusCode() {
        return statusCode;
    }
}
package co.kr.muldum.adapter.in.web;

import co.kr.muldum.adapter.in.web.dto.ErrorResponse;
import co.kr.muldum.domain.exception.InvalidAuthorizationCodeException;
import co.kr.muldum.domain.exception.UnregisteredUserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UnregisteredUserException.class)
    public ResponseEntity<ErrorResponse> handleUnregisteredUserException(UnregisteredUserException ex) {
        ErrorResponse errorResponse = ErrorResponse.of("UNREGISTERED_USER", "등록되지 않은 사용자입니다.");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(InvalidAuthorizationCodeException.class)
    public ResponseEntity<ErrorResponse> handleInvalidAuthorizationCodeException(InvalidAuthorizationCodeException ex) {
        ErrorResponse errorResponse = ErrorResponse.of(404, "인증 코드를 찾을 수 없습니다");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
        ErrorResponse errorResponse = ErrorResponse.of(500, "서버 내부 오류가 발생했습니다");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
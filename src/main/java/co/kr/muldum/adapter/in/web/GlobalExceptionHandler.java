package co.kr.muldum.adapter.in.web;

import co.kr.muldum.adapter.in.web.dto.ErrorResponse;
import co.kr.muldum.domain.exception.InvalidAuthorizationCodeException;
import co.kr.muldum.domain.exception.UnregisteredUserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(UnregisteredUserException.class)
    public ResponseEntity<ErrorResponse> handleUnregisteredUserException(UnregisteredUserException ex) {
        log.warn("Unregistered user exception: {}", ex.getMessage());
        ErrorResponse errorResponse = ErrorResponse.of("UNREGISTERED_USER", "등록되지 않은 사용자입니다.");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(InvalidAuthorizationCodeException.class)
    public ResponseEntity<ErrorResponse> handleInvalidAuthorizationCodeException(InvalidAuthorizationCodeException ex) {
        log.error("Invalid authorization code exception: {}", ex.getMessage(), ex);
        ErrorResponse errorResponse = ErrorResponse.of(404, "인증 코드를 찾을 수 없습니다");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
        log.error("Unexpected error occurred: {}", ex.getMessage(), ex);
        ErrorResponse errorResponse = ErrorResponse.of(500, "서버 내부 오류가 발생했습니다");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
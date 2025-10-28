package co.kr.muldum.domain.exception;

import org.springframework.http.HttpStatus;

public class InvalidGenerationException extends BusinessException {

    private static final String ERROR_CODE = "INVALID_GENERATION";
    private static final HttpStatus HTTP_STATUS = HttpStatus.BAD_REQUEST;

    public InvalidGenerationException(Integer generation) {
        super("유효하지 않은 세대 번호입니다: " + generation, HTTP_STATUS, ERROR_CODE);
    }
}
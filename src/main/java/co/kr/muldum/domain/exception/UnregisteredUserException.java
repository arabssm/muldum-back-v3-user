package co.kr.muldum.domain.exception;

public class UnregisteredUserException extends RuntimeException {
    public UnregisteredUserException(String email) {
        super("등록되지 않은 사용자입니다: " + email);
    }
}
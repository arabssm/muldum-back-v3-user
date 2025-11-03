package co.kr.muldum.application.port.out;

public interface DeleteRefreshTokenPort {
    void deleteByUserId(Long userId);
}
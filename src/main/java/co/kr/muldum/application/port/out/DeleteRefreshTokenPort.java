package co.kr.muldum.application.port.out;

public interface DeleteRefreshTokenPort {
    void deleteByEmail(String email);
    void deleteByRefreshToken(String refreshToken);
}
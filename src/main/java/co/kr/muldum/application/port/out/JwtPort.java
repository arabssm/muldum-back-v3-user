package co.kr.muldum.application.port.out;

public interface JwtPort {
    String generateAccessToken(Long userId, String email, String role);
}
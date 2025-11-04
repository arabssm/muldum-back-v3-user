package co.kr.muldum.application.port.out;

import java.util.UUID;

public interface JwtPort {
    String generateAccessToken(UUID userId, String email, String role);
}

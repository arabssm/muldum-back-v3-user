package co.kr.muldum.application.port.out;

import java.util.UUID;

public interface DeleteRefreshTokenPort {
    void deleteByUserId(UUID userId);
    void deleteByToken(String token);
}
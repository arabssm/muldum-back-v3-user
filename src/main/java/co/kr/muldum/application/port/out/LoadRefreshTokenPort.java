package co.kr.muldum.application.port.out;

import co.kr.muldum.domain.model.RefreshToken;

import java.util.Optional;
import java.util.UUID;

public interface LoadRefreshTokenPort {
    Optional<RefreshToken> findByToken(String token);
    Optional<RefreshToken> findByUserId(UUID userId);
}
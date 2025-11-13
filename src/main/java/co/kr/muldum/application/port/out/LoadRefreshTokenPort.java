package co.kr.muldum.application.port.out;

import co.kr.muldum.domain.model.RefreshToken;

import java.util.Optional;

public interface LoadRefreshTokenPort {
    Optional<RefreshToken> findByRefreshToken(String refreshToken);
    Optional<RefreshToken> findByEmail(String email);
}
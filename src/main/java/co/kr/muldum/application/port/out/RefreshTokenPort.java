package co.kr.muldum.application.port.out;

import co.kr.muldum.domain.model.RefreshToken;

import java.util.UUID;

public interface RefreshTokenPort {
    RefreshToken generateRefreshToken(UUID userId);
}
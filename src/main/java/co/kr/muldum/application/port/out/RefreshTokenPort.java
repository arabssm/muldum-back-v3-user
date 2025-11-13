package co.kr.muldum.application.port.out;

import co.kr.muldum.domain.model.RefreshToken;

public interface RefreshTokenPort {
    RefreshToken generateRefreshToken(String email);
}
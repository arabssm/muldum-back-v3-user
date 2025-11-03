package co.kr.muldum.application.port.out;

import co.kr.muldum.domain.model.RefreshToken;

public interface SaveRefreshTokenPort {
    void save(RefreshToken refreshToken);
}
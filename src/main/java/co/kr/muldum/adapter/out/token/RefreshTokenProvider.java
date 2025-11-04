package co.kr.muldum.adapter.out.token;

import co.kr.muldum.application.port.out.RefreshTokenPort;
import co.kr.muldum.domain.model.RefreshToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class RefreshTokenProvider implements RefreshTokenPort {

    private final long refreshTokenExpiration;

    public RefreshTokenProvider(@Value("${jwt.refresh-token-expiration}") long refreshTokenExpiration) {
        this.refreshTokenExpiration = refreshTokenExpiration;
    }

    @Override
    public RefreshToken generateRefreshToken(UUID userId) {
        String token = UUID.randomUUID().toString();
        LocalDateTime expiryDate = LocalDateTime.now().plusSeconds(refreshTokenExpiration / 1000);

        return RefreshToken.of(token, userId, expiryDate);
    }
}
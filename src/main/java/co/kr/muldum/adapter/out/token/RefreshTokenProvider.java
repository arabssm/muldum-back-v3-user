package co.kr.muldum.adapter.out.token;

import co.kr.muldum.application.port.out.RefreshTokenPort;
import co.kr.muldum.domain.model.RefreshToken;
import co.kr.muldum.domain.model.RefreshTokenFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class RefreshTokenProvider implements RefreshTokenPort {

    private final long refreshTokenExpiration;
    private final Clock clock;

    public RefreshTokenProvider(@Value("${jwt.refresh-token-expiration}") long refreshTokenExpiration,
                                Clock clock) {
        this.refreshTokenExpiration = refreshTokenExpiration;
        this.clock = clock;
    }

    @Override
    public RefreshToken generateRefreshToken(UUID userId) {
        String token = UUID.randomUUID().toString();
        LocalDateTime expiryDate = LocalDateTime.now(clock).plusSeconds(refreshTokenExpiration / 1000);

        return RefreshToken.createNew(token, userId, expiryDate, clock);
    }
}

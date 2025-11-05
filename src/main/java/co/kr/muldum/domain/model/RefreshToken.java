package co.kr.muldum.domain.model;

import co.kr.muldum.domain.validator.RefreshTokenValidator;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class RefreshToken {
    private final String token;
    private final UUID userId;
    private final LocalDateTime expiryDate;

    private RefreshToken(String token, UUID userId, LocalDateTime expiryDate) {
        this.token = token;
        this.userId = userId;
        this.expiryDate = expiryDate;
    }

    public static RefreshToken of(String token, UUID userId, LocalDateTime expiryDate) {
        RefreshTokenValidator.validateToken(token);
        RefreshTokenValidator.validateUserId(userId);
        RefreshTokenValidator.validateExpiryDate(expiryDate);

        return new RefreshToken(token, userId, expiryDate);
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiryDate);
    }
}
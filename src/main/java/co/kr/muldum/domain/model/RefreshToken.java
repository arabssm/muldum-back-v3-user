package co.kr.muldum.domain.model;

import co.kr.muldum.domain.exception.InvalidRefreshTokenException;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class RefreshToken {
    private final String token;
    private final UUID userId;
    private final LocalDateTime expiryDate;

    private RefreshToken(String token, UUID userId, LocalDateTime expiryDate) {
        validateToken(token);
        validateUserId(userId);
        validateExpiryDate(expiryDate);

        this.token = token;
        this.userId = userId;
        this.expiryDate = expiryDate;
    }

    public static RefreshToken of(String token, UUID userId, LocalDateTime expiryDate) {
        return new RefreshToken(token, userId, expiryDate);
    }

    private void validateToken(String token) {
        if (token == null || token.trim().isEmpty()) {
            throw new IllegalArgumentException("Refresh token cannot be null or empty");
        }
    }

    private void validateUserId(UUID userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
    }

    private void validateExpiryDate(LocalDateTime expiryDate) {
        if (expiryDate == null) {
            throw new IllegalArgumentException("Expiry date cannot be null");
        }
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiryDate);
    }

    /**
     * 토큰이 만료되지 않았는지 검증합니다.
     * 도메인 비즈니스 규칙: 만료된 토큰은 사용할 수 없음
     *
     * @throws InvalidRefreshTokenException 토큰이 만료된 경우
     */
    public void validateNotExpired() {
        if (isExpired()) {
            throw new InvalidRefreshTokenException("유효하지 않은 리프레시 토큰입니다");
        }
    }
}
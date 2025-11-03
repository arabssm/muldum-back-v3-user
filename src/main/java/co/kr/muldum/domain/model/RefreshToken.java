package co.kr.muldum.domain.model;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RefreshToken {
    private final String token;
    private final Long userId;
    private final LocalDateTime expiryDate;

    private RefreshToken(String token, Long userId, LocalDateTime expiryDate) {
        validateToken(token);
        validateUserId(userId);
        validateExpiryDate(expiryDate);

        this.token = token;
        this.userId = userId;
        this.expiryDate = expiryDate;
    }

    public static RefreshToken of(String token, Long userId, LocalDateTime expiryDate) {
        return new RefreshToken(token, userId, expiryDate);
    }

    private void validateToken(String token) {
        if (token == null || token.trim().isEmpty()) {
            throw new IllegalArgumentException("Refresh token cannot be null or empty");
        }
    }

    private void validateUserId(Long userId) {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("User ID must be positive");
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
}
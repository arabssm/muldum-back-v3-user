package co.kr.muldum.domain.validator;

import co.kr.muldum.domain.exception.RefreshTokenValidationException;

import java.time.LocalDateTime;
import java.util.UUID;

public class RefreshTokenValidator {

    private RefreshTokenValidator() {
        // Utility class - prevent instantiation
    }

    public static void validateToken(String token) {
        if (token == null || token.trim().isEmpty()) {
            throw new RefreshTokenValidationException("Refresh token cannot be null or empty");
        }
    }

    public static void validateUserId(UUID userId) {
        if (userId == null) {
            throw new RefreshTokenValidationException("User ID cannot be null");
        }
    }

    public static void validateExpiryDate(LocalDateTime expiryDate) {
        if (expiryDate == null) {
            throw new RefreshTokenValidationException("Expiry date cannot be null");
        }
    }
}
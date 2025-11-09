package co.kr.muldum.domain.model;

import co.kr.muldum.global.exception.InvalidRefreshTokenException;
import lombok.Getter;

import java.time.Clock;
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

    public static RefreshToken createNew(String token,
                                         UUID userId,
                                         LocalDateTime expiryDate,
                                         Clock clock) {
        validate(token, userId, expiryDate);

        LocalDateTime baselineNow = LocalDateTime.now(clock);

        if (!expiryDate.isAfter(baselineNow)) {
            throw new InvalidRefreshTokenException("Refresh token expiry must be in the future");
        }

        return new RefreshToken(token.trim(), userId, expiryDate);
    }

    public static RefreshToken from(String token,
                                    UUID userId,
                                    LocalDateTime expiryDate,
                                    Clock clock) {
        validate(token, userId, expiryDate);
        return new RefreshToken(token.trim(), userId, expiryDate);
    }

    private static void validate(String token, UUID userId, LocalDateTime expiryDate) {
        if (token == null || token.isBlank()) {
            throw new InvalidRefreshTokenException("Refresh token cannot be null or empty");
        }
        if (userId == null) {
            throw new InvalidRefreshTokenException("Refresh token requires a user identifier");
        }
        if (expiryDate == null) {
            throw new InvalidRefreshTokenException("Refresh token expiry date cannot be null");
        }
    }

    public boolean isExpired(Clock clock) {
        return !expiryDate.isAfter(LocalDateTime.now(clock));
    }

    public boolean isExpired() {
        return isExpired(Clock.systemUTC());
    }

    public void validateNotExpired() {
        if (isExpired()) {
            throw new InvalidRefreshTokenException("유효하지 않은 리프레시 토큰입니다");
        }
    }
}

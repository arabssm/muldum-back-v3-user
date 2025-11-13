package co.kr.muldum.domain.model;

import co.kr.muldum.global.exception.InvalidRefreshTokenException;
import lombok.Getter;

@Getter
public class RefreshToken {

    private final String email;
    private final String refreshToken;

    private RefreshToken(String email, String refreshToken) {
        this.email = email;
        this.refreshToken = refreshToken;
    }

    public static RefreshToken createNew(String email, String refreshToken) {
        validate(email, refreshToken);
        return new RefreshToken(email.trim(), refreshToken.trim());
    }

    public static RefreshToken from(String email, String refreshToken) {
        validate(email, refreshToken);
        return new RefreshToken(email.trim(), refreshToken.trim());
    }

    private static void validate(String email, String refreshToken) {
        if (email == null || email.isBlank()) {
            throw new InvalidRefreshTokenException("Email cannot be null or empty");
        }
        if (refreshToken == null || refreshToken.isBlank()) {
            throw new InvalidRefreshTokenException("Refresh token cannot be null or empty");
        }
    }
}

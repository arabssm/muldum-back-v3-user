package co.kr.muldum.domain.validator;

import co.kr.muldum.global.exception.RefreshTokenValidationException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Component
public class RefreshTokenValidator implements DomainValidator<RefreshTokenValidator.Target> {

    @Override
    public void validate(Target target) {
        validateToken(target.token());
        validateUserId(target.userId());
        validateExpiryDate(target.expiryDate());
    }

    public void validateToken(String token) {
        if (token == null || token.isBlank()) {
            throw new RefreshTokenValidationException("Refresh token cannot be null or empty",
                    Map.of("field", "token"));
        }
    }

    public void validateUserId(UUID userId) {
        if (userId == null) {
            throw new RefreshTokenValidationException("User ID cannot be null", Map.of("field", "userId"));
        }
    }

    public void validateExpiryDate(LocalDateTime expiryDate) {
        if (expiryDate == null) {
            throw new RefreshTokenValidationException("Expiry date cannot be null", Map.of("field", "expiryDate"));
        }
    }

    public record Target(String token, UUID userId, LocalDateTime expiryDate) {
    }
}

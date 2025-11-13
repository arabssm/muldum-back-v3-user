package co.kr.muldum.domain.validator;

import co.kr.muldum.global.exception.RefreshTokenValidationException;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RefreshTokenValidator implements DomainValidator<RefreshTokenValidator.Target> {

    @Override
    public void validate(Target target) {
        validateEmail(target.email());
        validateRefreshToken(target.refreshToken());
    }

    public void validateEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new RefreshTokenValidationException("Email cannot be null or empty",
                    Map.of("field", "email"));
        }
    }

    public void validateRefreshToken(String refreshToken) {
        if (refreshToken == null || refreshToken.isBlank()) {
            throw new RefreshTokenValidationException("Refresh token cannot be null or empty",
                    Map.of("field", "refreshToken"));
        }
    }

    public record Target(String email, String refreshToken) {
    }
}

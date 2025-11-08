package co.kr.muldum.domain.model;

import co.kr.muldum.domain.validator.RefreshTokenValidator;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class RefreshTokenFactory {

    private final RefreshTokenValidator refreshTokenValidator;

    public RefreshTokenFactory(RefreshTokenValidator refreshTokenValidator) {
        this.refreshTokenValidator = refreshTokenValidator;
    }

    public RefreshToken create(RefreshTokenCreateCommand command) {
        refreshTokenValidator.validate(new RefreshTokenValidator.Target(
                command.token(),
                command.userId(),
                command.expiryDate()
        ));

        return new RefreshToken(command.token(), command.userId(), command.expiryDate());
    }

    public record RefreshTokenCreateCommand(String token, UUID userId, LocalDateTime expiryDate) {
    }
}

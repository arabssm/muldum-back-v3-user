package co.kr.muldum.domain.model;

import co.kr.muldum.domain.validator.RefreshTokenValidator;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class RefreshTokenFactory {

    private final RefreshTokenValidator refreshTokenValidator;
    private final Clock clock;

    public RefreshTokenFactory(RefreshTokenValidator refreshTokenValidator, Clock clock) {
        this.refreshTokenValidator = refreshTokenValidator;
        this.clock = clock;
    }

    public RefreshToken create(RefreshTokenCreateCommand command) {
        refreshTokenValidator.validate(new RefreshTokenValidator.Target(
                command.token(),
                command.userId(),
                command.expiryDate()
        ));

        return RefreshToken.from(command.token(), command.userId(), command.expiryDate(), clock);
    }

    public record RefreshTokenCreateCommand(String token, UUID userId, LocalDateTime expiryDate) {
    }
}

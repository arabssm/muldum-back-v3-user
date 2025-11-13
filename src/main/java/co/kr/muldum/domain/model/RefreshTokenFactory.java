package co.kr.muldum.domain.model;

import co.kr.muldum.domain.validator.RefreshTokenValidator;
import org.springframework.stereotype.Component;

@Component
public class RefreshTokenFactory {

    private final RefreshTokenValidator refreshTokenValidator;

    public RefreshTokenFactory(RefreshTokenValidator refreshTokenValidator) {
        this.refreshTokenValidator = refreshTokenValidator;
    }

    public RefreshToken create(RefreshTokenCreateCommand command) {
        refreshTokenValidator.validate(new RefreshTokenValidator.Target(
                command.email(),
                command.refreshToken()
        ));

        return RefreshToken.from(command.email(), command.refreshToken());
    }

    public record RefreshTokenCreateCommand(String email, String refreshToken) {
    }
}

package co.kr.muldum.application.service;

import co.kr.muldum.application.port.in.GoogleLoginUseCase;
import co.kr.muldum.application.port.in.command.GoogleLoginCommand;
import co.kr.muldum.application.port.in.response.LoginResponse;
import co.kr.muldum.application.port.out.GoogleOAuthPort;
import co.kr.muldum.application.port.out.JwtPort;
import co.kr.muldum.application.port.out.LoadUserPort;
import co.kr.muldum.application.port.out.RefreshTokenPort;
import co.kr.muldum.application.port.out.SaveRefreshTokenPort;
import co.kr.muldum.domain.exception.UnregisteredUserException;
import co.kr.muldum.domain.model.RefreshToken;
import co.kr.muldum.domain.model.User;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class GoogleLoginService implements GoogleLoginUseCase {

    private static final Logger log = LoggerFactory.getLogger(GoogleLoginService.class);

    private final GoogleOAuthPort googleOAuthPort;
    private final LoadUserPort loadUserPort;
    private final JwtPort jwtPort;
    private final RefreshTokenPort refreshTokenPort;
    private final SaveRefreshTokenPort saveRefreshTokenPort;

    public GoogleLoginService(
            GoogleOAuthPort googleOAuthPort,
            LoadUserPort loadUserPort,
            JwtPort jwtPort,
            RefreshTokenPort refreshTokenPort,
            SaveRefreshTokenPort saveRefreshTokenPort) {
        this.googleOAuthPort = googleOAuthPort;
        this.loadUserPort = loadUserPort;
        this.jwtPort = jwtPort;
        this.refreshTokenPort = refreshTokenPort;
        this.saveRefreshTokenPort = saveRefreshTokenPort;
    }

    @Override
    @Transactional
    public LoginResponse login(GoogleLoginCommand command) {

        String emailFromProvider = googleOAuthPort.getEmailFromAuthCode(command.getAuthorizationCode());

        String normalizedEmail = normalizeEmail(emailFromProvider);

        if (normalizedEmail == null || normalizedEmail.isEmpty()) {
            throw new UnregisteredUserException(emailFromProvider);
        }

        User user = loadUserPort.findByEmail(normalizedEmail)
                .orElseThrow(() -> new UnregisteredUserException(normalizedEmail));

        String accessToken = jwtPort.generateAccessToken(
                user.getId(),
                user.getEmail(),
                user.getUserRole().getValue()
        );

        RefreshToken refreshToken = refreshTokenPort.generateRefreshToken(user.getId());
        saveRefreshTokenPort.save(refreshToken);

        return LoginResponse.of(
                user.getId(),
                user.getName(),
                user.getUserRole(),
                user.getTeamId(),
                accessToken,
                refreshToken.getToken()
        );
    }

    private String normalizeEmail(String email) {
        if (email == null) {
            return null;
        }
        return email.trim().toLowerCase(Locale.ROOT);
    }
}

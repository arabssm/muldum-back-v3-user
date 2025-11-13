package co.kr.muldum.application.service;

import co.kr.muldum.application.port.in.GoogleLoginUseCase;
import co.kr.muldum.application.port.in.command.GoogleLoginCommand;
import co.kr.muldum.application.port.in.response.LoginResponse;
import co.kr.muldum.application.port.out.GoogleOAuthPort;
import co.kr.muldum.application.port.out.JwtPort;
import co.kr.muldum.application.port.out.LoadUserPort;
import co.kr.muldum.application.port.out.RefreshTokenPort;
import co.kr.muldum.application.port.out.SaveRefreshTokenPort;
import co.kr.muldum.domain.model.RefreshToken;
import co.kr.muldum.domain.model.User;
import co.kr.muldum.domain.service.EmailNormalizationService;
import co.kr.muldum.global.exception.UnregisteredUserException;
import co.kr.muldum.infrastructure.messaging.UserEventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class GoogleLoginServiceImpl implements GoogleLoginUseCase {

    private static final Logger log = LoggerFactory.getLogger(GoogleLoginServiceImpl.class);

    private final GoogleOAuthPort googleOAuthPort;
    private final LoadUserPort loadUserPort;
    private final JwtPort jwtPort;
    private final RefreshTokenPort refreshTokenPort;
    private final SaveRefreshTokenPort saveRefreshTokenPort;
    private final EmailNormalizationService emailNormalizationService;
    private final UserEventPublisher userEventPublisher;

    public GoogleLoginServiceImpl(
            GoogleOAuthPort googleOAuthPort,
            LoadUserPort loadUserPort,
            JwtPort jwtPort,
            RefreshTokenPort refreshTokenPort,
            SaveRefreshTokenPort saveRefreshTokenPort,
            EmailNormalizationService emailNormalizationService,
            UserEventPublisher userEventPublisher) {
        this.googleOAuthPort = googleOAuthPort;
        this.loadUserPort = loadUserPort;
        this.jwtPort = jwtPort;
        this.refreshTokenPort = refreshTokenPort;
        this.saveRefreshTokenPort = saveRefreshTokenPort;
        this.emailNormalizationService = emailNormalizationService;
        this.userEventPublisher = userEventPublisher;
    }

    @Override
    @Transactional
    public LoginResponse login(GoogleLoginCommand command) {
        // OAuth 인증 코드로 이메일 획득
        String emailFromProvider = googleOAuthPort.getEmailFromAuthCode(command.getAuthorizationCode());

        // 도메인 서비스를 통한 이메일 정규화
        String normalizedEmail = emailNormalizationService.normalize(emailFromProvider);

        // 사용자 조회
        User user = loadUserPort.findByEmail(normalizedEmail)
                .orElseThrow(() -> new UnregisteredUserException(normalizedEmail));

        // JWT 토큰 생성
        String accessToken = jwtPort.generateAccessToken(
                user.getId(),
                user.getEmail(),
                user.getUserType().getValue()
        );

        // 리프레시 토큰 생성 및 저장
        RefreshToken refreshToken = refreshTokenPort.generateRefreshToken(user.getEmail());
        saveRefreshTokenPort.save(refreshToken);

        // Publish login event for downstream consumers
        userEventPublisher.publishLoginEvent(user);

        return LoginResponse.of(
                user.getId(),
                user.getName(),
                user.getUserType(),
                accessToken,
                refreshToken.getRefreshToken()
        );
    }
}

package co.kr.muldum.application.service;

import co.kr.muldum.application.port.in.RefreshAccessTokenUseCase;
import co.kr.muldum.application.port.in.command.RefreshTokenCommand;
import co.kr.muldum.application.port.in.response.RefreshTokenResponse;
import co.kr.muldum.application.port.out.JwtPort;
import co.kr.muldum.application.port.out.LoadRefreshTokenPort;
import co.kr.muldum.application.port.out.LoadUserPort;
import co.kr.muldum.domain.model.RefreshToken;
import co.kr.muldum.domain.model.User;
import co.kr.muldum.global.exception.InvalidRefreshTokenException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class RefreshAccessTokenServiceImpl implements RefreshAccessTokenUseCase {

    private final LoadRefreshTokenPort loadRefreshTokenPort;
    private final LoadUserPort loadUserPort;
    private final JwtPort jwtPort;

    public RefreshAccessTokenServiceImpl(
            LoadRefreshTokenPort loadRefreshTokenPort,
            LoadUserPort loadUserPort,
            JwtPort jwtPort) {
        this.loadRefreshTokenPort = loadRefreshTokenPort;
        this.loadUserPort = loadUserPort;
        this.jwtPort = jwtPort;
    }

    @Override
    public RefreshTokenResponse refresh(RefreshTokenCommand command) {
        // 리프레시 토큰 조회
        RefreshToken refreshToken = loadRefreshTokenPort.findByToken(command.getRefreshToken())
                .orElseThrow(() -> new InvalidRefreshTokenException("유효하지 않은 리프레시 토큰입니다"));

        // 토큰 만료 검증
        if (refreshToken.isExpired()) {
            throw new InvalidRefreshTokenException("유효하지 않은 리프레시 토큰입니다");
        }

        // 사용자 조회
        User user = loadUserPort.findById(refreshToken.getUserId())
                .orElseThrow(() -> new InvalidRefreshTokenException("유효하지 않은 리프레시 토큰입니다"));

        // 새로운 액세스 토큰 생성
        String accessToken = jwtPort.generateAccessToken(
                user.getId(),
                user.getEmail(),
                user.getUserRole().getValue()
        );

        return RefreshTokenResponse.of(accessToken);
    }
}
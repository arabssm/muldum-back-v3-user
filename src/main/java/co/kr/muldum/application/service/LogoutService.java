package co.kr.muldum.application.service;

import co.kr.muldum.application.port.in.LogoutUseCase;
import co.kr.muldum.application.port.in.command.LogoutCommand;
import co.kr.muldum.application.port.out.DeleteRefreshTokenPort;
import co.kr.muldum.application.port.out.LoadRefreshTokenPort;
import co.kr.muldum.domain.exception.InvalidRefreshTokenException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LogoutService implements LogoutUseCase {

    private final LoadRefreshTokenPort loadRefreshTokenPort;
    private final DeleteRefreshTokenPort deleteRefreshTokenPort;

    public LogoutService(LoadRefreshTokenPort loadRefreshTokenPort, DeleteRefreshTokenPort deleteRefreshTokenPort) {
        this.loadRefreshTokenPort = loadRefreshTokenPort;
        this.deleteRefreshTokenPort = deleteRefreshTokenPort;
    }

    @Override
    public void logout(LogoutCommand command) {
        loadRefreshTokenPort.findByToken(command.getRefreshToken())
                .orElseThrow(() -> new InvalidRefreshTokenException("유효한 리프레시 토큰이 없습니다."));

        deleteRefreshTokenPort.deleteByToken(command.getRefreshToken());
    }
}
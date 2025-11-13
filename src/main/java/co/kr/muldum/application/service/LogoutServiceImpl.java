package co.kr.muldum.application.service;

import co.kr.muldum.application.port.in.LogoutUseCase;
import co.kr.muldum.application.port.in.command.LogoutCommand;
import co.kr.muldum.application.port.out.DeleteRefreshTokenPort;
import co.kr.muldum.application.port.out.LoadRefreshTokenPort;
import co.kr.muldum.global.exception.InvalidRefreshTokenException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LogoutServiceImpl implements LogoutUseCase {

    private final LoadRefreshTokenPort loadRefreshTokenPort;
    private final DeleteRefreshTokenPort deleteRefreshTokenPort;

    public LogoutServiceImpl(LoadRefreshTokenPort loadRefreshTokenPort, DeleteRefreshTokenPort deleteRefreshTokenPort) {
        this.loadRefreshTokenPort = loadRefreshTokenPort;
        this.deleteRefreshTokenPort = deleteRefreshTokenPort;
    }

    @Override
    public void logout(LogoutCommand command) {
        // 리프레시 토큰 존재 확인
        loadRefreshTokenPort.findByRefreshToken(command.getRefreshToken())
                .orElseThrow(() -> new InvalidRefreshTokenException("유효한 리프레시 토큰이 없습니다."));

        // 리프레시 토큰 삭제
        deleteRefreshTokenPort.deleteByRefreshToken(command.getRefreshToken());
    }
}
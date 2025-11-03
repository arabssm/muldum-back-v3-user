package co.kr.muldum.adapter.in.web;

import co.kr.muldum.adapter.in.web.dto.LogoutRequest;
import co.kr.muldum.adapter.in.web.dto.LogoutResponseDto;
import co.kr.muldum.application.port.in.LogoutUseCase;
import co.kr.muldum.application.port.in.command.LogoutCommand;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ara/auth")
public class LogoutController {

    private final LogoutUseCase logoutUseCase;

    public LogoutController(LogoutUseCase logoutUseCase) {
        this.logoutUseCase = logoutUseCase;
    }

    @PostMapping("/logout")
    public ResponseEntity<LogoutResponseDto> logout(@Valid @RequestBody LogoutRequest request) {
        LogoutCommand command = LogoutCommand.of(request.getRefreshToken());
        logoutUseCase.logout(command);
        return ResponseEntity.ok(LogoutResponseDto.of("로그아웃 되었습니다."));
    }
}
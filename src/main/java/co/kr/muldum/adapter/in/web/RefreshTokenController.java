package co.kr.muldum.adapter.in.web;

import co.kr.muldum.adapter.in.web.dto.RefreshTokenRequest;
import co.kr.muldum.adapter.in.web.dto.RefreshTokenResponseDto;
import co.kr.muldum.application.port.in.RefreshAccessTokenUseCase;
import co.kr.muldum.application.port.in.command.RefreshTokenCommand;
import co.kr.muldum.application.port.in.response.RefreshTokenResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class RefreshTokenController {

    private final RefreshAccessTokenUseCase refreshAccessTokenUseCase;

    public RefreshTokenController(RefreshAccessTokenUseCase refreshAccessTokenUseCase) {
        this.refreshAccessTokenUseCase = refreshAccessTokenUseCase;
    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshTokenResponseDto> refresh(@Valid @RequestBody RefreshTokenRequest request) {
        RefreshTokenCommand command = RefreshTokenCommand.of(request.getRefreshToken());
        RefreshTokenResponse response = refreshAccessTokenUseCase.refresh(command);
        return ResponseEntity.ok(RefreshTokenResponseDto.from(response));
    }
}
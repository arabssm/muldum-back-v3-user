package co.kr.muldum.adapter.in.web;

import co.kr.muldum.adapter.in.web.dto.GoogleLoginRequest;
import co.kr.muldum.adapter.in.web.dto.GoogleLoginResponse;
import co.kr.muldum.application.port.in.GoogleLoginUseCase;
import co.kr.muldum.application.port.in.command.GoogleLoginCommand;
import co.kr.muldum.application.port.in.response.LoginResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ara/auth")
public class GoogleLoginController {

    private final GoogleLoginUseCase googleLoginUseCase;

    public GoogleLoginController(GoogleLoginUseCase googleLoginUseCase) {
        this.googleLoginUseCase = googleLoginUseCase;
    }

    @PostMapping("/login/google")
    public ResponseEntity<GoogleLoginResponse> login(@Valid @RequestBody GoogleLoginRequest request) {
        GoogleLoginCommand command = GoogleLoginCommand.of(request.getAuthorizationCode());
        LoginResponse loginResponse = googleLoginUseCase.login(command);
        return ResponseEntity.ok(GoogleLoginResponse.from(loginResponse));
    }
}
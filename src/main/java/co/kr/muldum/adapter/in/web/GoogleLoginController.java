package co.kr.muldum.adapter.in.web;

import co.kr.muldum.adapter.in.web.dto.GoogleLoginRequest;
import co.kr.muldum.adapter.in.web.dto.GoogleLoginResponse;
import co.kr.muldum.application.port.in.GoogleLoginUseCase;
import co.kr.muldum.application.port.in.command.GoogleLoginCommand;
import co.kr.muldum.application.port.in.response.LoginResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@RestController
@RequestMapping("/ara/auth")
public class GoogleLoginController {

    private final GoogleLoginUseCase googleLoginUseCase;
    private final String googleClientId;
    private final String googleRedirectUri;

    public GoogleLoginController(
            GoogleLoginUseCase googleLoginUseCase,
            @Value("${google.oauth.client-id}") String googleClientId,
            @Value("${google.oauth.redirect-uri}") String googleRedirectUri) {
        this.googleLoginUseCase = googleLoginUseCase;
        this.googleClientId = googleClientId;
        this.googleRedirectUri = googleRedirectUri;
    }

    /**
     * 테스트용: 브라우저에서 http://localhost:8080/ara/auth/login/google 접속하면
     * Google 로그인 페이지로 리다이렉트됩니다.
     */
    @GetMapping("/login/google")
    public RedirectView redirectToGoogleLogin() {
        String googleAuthUrl = String.format(
            "https://accounts.google.com/o/oauth2/v2/auth?" +
            "client_id=%s&" +
            "redirect_uri=%s&" +
            "response_type=code&" +
            "scope=email profile&" +
            "access_type=offline&" +
            "prompt=consent",
            googleClientId,
            googleRedirectUri
        );
        return new RedirectView(googleAuthUrl);
    }

    /**
     * Google 로그인 후 리다이렉트되는 엔드포인트
     * 인증 코드를 받아서 자동으로 로그인 처리합니다.
     */
    @GetMapping("/login/oauth2/code/google")
    public ResponseEntity<?> handleGoogleCallback(@RequestParam("code") String code) {
        try {
            GoogleLoginCommand command = GoogleLoginCommand.of(code);
            LoginResponse loginResponse = googleLoginUseCase.login(command);

            return ResponseEntity.ok(Map.of(
                "message", "로그인 성공!",
                "role", loginResponse.getRole().toString(),
                "userId", loginResponse.getUserId(),
                "name", loginResponse.getName(),
                "accessToken", loginResponse.getAccessToken(),
                "teamId", loginResponse.getTeamId().orElse(null)
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                "error", "로그인 실패",
                "message", e.getMessage()
            ));
        }
    }

    @PostMapping("/login/google")
    public ResponseEntity<GoogleLoginResponse> login(@Valid @RequestBody GoogleLoginRequest request) {
        GoogleLoginCommand command = GoogleLoginCommand.of(request.getAuthorizationCode());
        LoginResponse loginResponse = googleLoginUseCase.login(command);
        return ResponseEntity.ok(GoogleLoginResponse.from(loginResponse));
    }
}
package co.kr.muldum.adapter.in.web.dto;

import co.kr.muldum.application.port.in.response.LoginResponse;
import co.kr.muldum.domain.model.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class GoogleLoginResponse {

    private final Role role;
    private final Long userId;
    private final String name;
    private final Long teamId;
    private final String accessToken;
    private final String refreshToken;

    private GoogleLoginResponse(Role role, Long userId, String name, Long teamId, String accessToken, String refreshToken) {
        this.role = role;
        this.userId = userId;
        this.name = name;
        this.teamId = teamId;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public static GoogleLoginResponse from(LoginResponse loginResponse) {
        return new GoogleLoginResponse(
            loginResponse.getRole(),
            loginResponse.getUserId(),
            loginResponse.getName(),
            loginResponse.getTeamId(),
            loginResponse.getAccessToken(),
            loginResponse.getRefreshToken()
        );
    }
}

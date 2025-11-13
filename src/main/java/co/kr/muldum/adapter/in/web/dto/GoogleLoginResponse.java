package co.kr.muldum.adapter.in.web.dto;

import co.kr.muldum.application.port.in.response.LoginResponse;
import co.kr.muldum.domain.model.UserType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class GoogleLoginResponse {

    private final UserType userType;
    private final UUID userId;
    private final String name;
    private final String accessToken;
    private final String refreshToken;

    private GoogleLoginResponse(UserType userType, UUID userId, String name, String accessToken, String refreshToken) {
        this.userType = userType;
        this.userId = userId;
        this.name = name;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public static GoogleLoginResponse from(LoginResponse loginResponse) {
        return new GoogleLoginResponse(
            loginResponse.getUserType(),
            loginResponse.getUserId(),
            loginResponse.getName(),
            loginResponse.getAccessToken(),
            loginResponse.getRefreshToken()
        );
    }
}

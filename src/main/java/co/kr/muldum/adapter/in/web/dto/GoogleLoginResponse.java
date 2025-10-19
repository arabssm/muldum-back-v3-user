package co.kr.muldum.adapter.in.web.dto;

import co.kr.muldum.application.port.in.response.LoginResponse;
import co.kr.muldum.domain.model.Role;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GoogleLoginResponse {

    private Role role;
    private Long userId;
    private String name;
    private Long teamId;
    private String accessToken;

    private GoogleLoginResponse() {
    }

    public static GoogleLoginResponse from(LoginResponse loginResponse) {
        GoogleLoginResponse response = new GoogleLoginResponse();
        response.role = loginResponse.getRole();
        response.userId = loginResponse.getUserId();
        response.name = loginResponse.getName();
        response.teamId = loginResponse.getTeamId().orElse(null);
        response.accessToken = loginResponse.getAccessToken();
        return response;
    }

    public Role getRole() {
        return role;
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public Long getTeamId() {
        return teamId;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
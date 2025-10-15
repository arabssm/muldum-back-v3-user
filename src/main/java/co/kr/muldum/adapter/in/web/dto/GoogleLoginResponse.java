package co.kr.muldum.adapter.in.web.dto;

import co.kr.muldum.application.port.in.response.LoginResponse;
import co.kr.muldum.domain.model.UserType;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GoogleLoginResponse {

    private UserType userType;
    private Long userId;
    private String name;
    private Long teamId;
    private String role;
    private String accessToken;

    private GoogleLoginResponse() {
    }

    public static GoogleLoginResponse from(LoginResponse loginResponse) {
        GoogleLoginResponse response = new GoogleLoginResponse();
        response.userType = loginResponse.getUserType();
        response.userId = loginResponse.getUserId();
        response.name = loginResponse.getName();
        response.teamId = loginResponse.getTeamId();
        response.role = loginResponse.getRole();
        response.accessToken = loginResponse.getAccessToken();
        return response;
    }

    public UserType getUserType() {
        return userType;
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

    public String getRole() {
        return role;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
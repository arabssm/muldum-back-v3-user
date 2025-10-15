package co.kr.muldum.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;

public class GoogleLoginRequest {

    @NotBlank(message = "Authorization code is required")
    private String authorizationCode;

    public GoogleLoginRequest() {
    }

    public GoogleLoginRequest(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }
}
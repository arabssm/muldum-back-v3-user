package co.kr.muldum.adapter.in.web.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public class GoogleLoginRequest {

    @NotBlank(message = "Authorization code is required")
    private final String authorizationCode;

    @JsonCreator
    public GoogleLoginRequest(@JsonProperty("authorizationCode") String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }
}

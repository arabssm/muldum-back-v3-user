package co.kr.muldum.application.port.in.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class RefreshTokenResponse {
    private final String accessToken;

    public static RefreshTokenResponse of(String accessToken) {
        return new RefreshTokenResponse(accessToken);
    }
}
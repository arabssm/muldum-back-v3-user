package co.kr.muldum.adapter.in.web.dto;

import co.kr.muldum.application.port.in.response.RefreshTokenResponse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class RefreshTokenResponseDto {
    private final String accessToken;

    public static RefreshTokenResponseDto from(RefreshTokenResponse response) {
        return new RefreshTokenResponseDto(response.getAccessToken());
    }
}
package co.kr.muldum.application.port.in.response;

import co.kr.muldum.domain.model.UserType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginResponse {
    private final UserType userType;
    private final UUID userId;
    private final String name;
    private final String accessToken;
    private final String refreshToken;

    public static LoginResponse of(UUID userId, String name, UserType userType, String accessToken, String refreshToken) {
        return new LoginResponse(userType, userId, name, accessToken, refreshToken);
    }
}

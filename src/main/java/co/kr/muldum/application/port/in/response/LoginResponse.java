package co.kr.muldum.application.port.in.response;

import co.kr.muldum.domain.model.Role;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginResponse {
    private final Role role;
    private final UUID userId;
    private final String name;
    private final Long teamId;
    private final String accessToken;
    private final String refreshToken;

    public static LoginResponse of(UUID userId, String name, Role role, Long teamId, String accessToken, String refreshToken) {
        return new LoginResponse(role, userId, name, teamId, accessToken, refreshToken);
    }
}

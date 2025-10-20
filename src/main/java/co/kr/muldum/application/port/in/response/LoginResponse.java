package co.kr.muldum.application.port.in.response;

import co.kr.muldum.domain.model.Role;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginResponse {
    private final Role role;
    private final Long userId;
    private final String name;
    private final Long teamId;
    private final String accessToken;

    public static LoginResponse ofStudent(Long userId, String name, Long teamId, String accessToken) {
        return new LoginResponse(Role.STUDENT, userId, name, teamId, accessToken);
    }

    public static LoginResponse ofTeacher(Long userId, String name, String accessToken) {
        return new LoginResponse(Role.TEACHER, userId, name, null, accessToken);
    }
}

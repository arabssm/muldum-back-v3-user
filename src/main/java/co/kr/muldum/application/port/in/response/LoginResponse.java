package co.kr.muldum.application.port.in.response;

import co.kr.muldum.domain.model.UserType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginResponse {
    private final UserType userType;
    private final Long userId;
    private final String name;
    private final Optional<Long> teamId;
    private final String role;
    private final String accessToken;

    public static LoginResponse ofStudent(Long userId, String name, Long teamId, String accessToken) {
        return new LoginResponse(UserType.STUDENT, userId, name, Optional.ofNullable(teamId), "student", accessToken);
    }

    public static LoginResponse ofTeacher(Long userId, String name, String accessToken) {
        return new LoginResponse(UserType.TEACHER, userId, name, Optional.empty(), "teacher", accessToken);
    }
}
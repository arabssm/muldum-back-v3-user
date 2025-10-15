package co.kr.muldum.application.port.in.response;

import co.kr.muldum.domain.model.UserType;

public class LoginResponse {
    private final UserType userType;
    private final Long userId;
    private final String name;
    private final Long teamId;
    private final String role;
    private final String accessToken;

    private LoginResponse(UserType userType, Long userId, String name, Long teamId, String role, String accessToken) {
        this.userType = userType;
        this.userId = userId;
        this.name = name;
        this.teamId = teamId;
        this.role = role;
        this.accessToken = accessToken;
    }

    public static LoginResponse ofStudent(Long userId, String name, Long teamId, String accessToken) {
        return new LoginResponse(UserType.STUDENT, userId, name, teamId, "student", accessToken);
    }

    public static LoginResponse ofTeacher(Long userId, String name, String accessToken) {
        return new LoginResponse(UserType.TEACHER, userId, name, null, "teacher", accessToken);
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
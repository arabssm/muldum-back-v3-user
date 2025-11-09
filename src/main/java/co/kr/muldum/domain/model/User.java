package co.kr.muldum.domain.model;

import lombok.Getter;

import java.util.UUID;

@Getter
public class User {
    private final UUID id;
    private final String name;
    private final Integer enrolledAt;
    private final String email;
    private final Integer classNo;
    private final Integer grade;
    private final Role userRole;
    private final Long teamId;

    protected User(UUID id,
                   String name,
                   Integer enrolledAt,
                   String email,
                   Integer classNo,
                   Integer grade,
                   Role userRole,
                   Long teamId) {
        this.id = id;
        this.name = name;
        this.enrolledAt = enrolledAt;
        this.email = email;
        this.classNo = classNo;
        this.grade = grade;
        this.userRole = userRole;
        this.teamId = teamId;
    }
}

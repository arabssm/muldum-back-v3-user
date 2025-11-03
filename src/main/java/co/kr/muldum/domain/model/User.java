package co.kr.muldum.domain.model;

import lombok.Getter;

@Getter
public class User {
    private final Long id;
    private final String name;
    private final Integer enrolledAt;
    private final String email;
    private final Integer classNo;
    private final Integer grade;
    private final Role userRole;
    private final Long teamId; // Member 테이블에서 조회한 값

    private User(Long id, String name, Integer enrolledAt, String email,
                Integer classNo, Integer grade, Role userRole, Long teamId) {
        validateEmail(email);
        validateName(name);

        this.id = id;
        this.name = name;
        this.enrolledAt = enrolledAt;
        this.email = email;
        this.classNo = classNo;
        this.grade = grade;
        this.userRole = userRole;
        this.teamId = teamId;
    }

    public static User of(Long id, String name, Integer enrolledAt, String email,
                          Integer classNo, Integer grade, Role userRole, Long teamId) {
        return new User(id, name, enrolledAt, email, classNo, grade, userRole, teamId);
    }

    private void validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            throw new IllegalArgumentException("Invalid email format: " + email);
        }
    }

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
    }
}

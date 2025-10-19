package co.kr.muldum.domain.model;

public class Student extends User {
    private final Long teamId;

    private Student(Long userId, String email, String name, Long teamId) {
        super(userId, email, name, Role.STUDENT);
        this.teamId = teamId;
    }

    public static Student create(String email, String name, Long teamId) {
        return new Student(null, email, name, teamId);
    }

    public static Student of(Long userId, String email, String name, Long teamId) {
        return new Student(userId, email, name, teamId);
    }

    public Long getTeamId() {
        return teamId;
    }

    @Override
    public Student withUserId(Long userId) {
        return new Student(userId, this.getEmail(), this.getName(), this.teamId);
    }
}
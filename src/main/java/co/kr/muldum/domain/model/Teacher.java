package co.kr.muldum.domain.model;

public class Teacher extends User {

    private Teacher(Long userId, String email, String name) {
        super(userId, email, name, UserType.TEACHER, Role.TEACHER);
    }

    public static Teacher create(String email, String name) {
        return new Teacher(null, email, name);
    }

    public static Teacher of(Long userId, String email, String name) {
        return new Teacher(userId, email, name);
    }

    @Override
    public Teacher withUserId(Long userId) {
        return new Teacher(userId, this.getEmail(), this.getName());
    }
}
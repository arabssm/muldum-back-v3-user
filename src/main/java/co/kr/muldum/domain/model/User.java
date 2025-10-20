package co.kr.muldum.domain.model;

import lombok.Getter;

@Getter
public abstract class User {
    private final Long userId;
    private final String email;
    private final String name;
    private final Role role;

    protected User(Long userId, String email, String name, Role role) {
        validateEmail(email);
        validateName(name);

        this.userId = userId;
        this.email = email;
        this.name = name;
        this.role = role;
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

    public abstract User withUserId(Long userId);
}
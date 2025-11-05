package co.kr.muldum.domain.validator;

import co.kr.muldum.domain.exception.UserValidationException;

public class UserValidator {

    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

    private UserValidator() {
        // Utility class - prevent instantiation
    }

    public static void validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new UserValidationException("Email cannot be null or empty");
        }
        if (!email.matches(EMAIL_PATTERN)) {
            throw new UserValidationException("Invalid email format: " + email);
        }
    }

    public static void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new UserValidationException("Name cannot be null or empty");
        }
    }

    public static void validateEnrolledAt(Integer enrolledAt) {
        if (enrolledAt == null) {
            throw new UserValidationException("Enrolled year cannot be null");
        }
        if (enrolledAt < 1900 || enrolledAt > 2100) {
            throw new UserValidationException("Invalid enrolled year: " + enrolledAt);
        }
    }

    public static void validateClassNo(Integer classNo) {
        if (classNo == null) {
            throw new UserValidationException("Class number cannot be null");
        }
        if (classNo < 1) {
            throw new UserValidationException("Class number must be positive: " + classNo);
        }
    }

    public static void validateGrade(Integer grade) {
        if (grade == null) {
            throw new UserValidationException("Grade cannot be null");
        }
        if (grade < 1 || grade > 3) {
            throw new UserValidationException("Grade must be between 1 and 3: " + grade);
        }
    }
}
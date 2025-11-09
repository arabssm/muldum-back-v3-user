package co.kr.muldum.domain.validator;

import co.kr.muldum.global.exception.UserValidationException;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.regex.Pattern;

@Component
public class UserValidator implements DomainValidator<UserValidator.Target> {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    @Override
    public void validate(Target target) {
        validateEmail(target.email());
        validateName(target.name());

        if (target.enrolledAt() != null) {
            validateEnrolledAt(target.enrolledAt());
        }

        if (target.classNo() != null) {
            validateClassNo(target.classNo());
        }

        if (target.grade() != null) {
            validateGrade(target.grade());
        }
    }

    public void validateEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new UserValidationException("Email cannot be null or empty", Map.of("field", "email"));
        }
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new UserValidationException("Invalid email format: " + email,
                    Map.of("field", "email", "rejectedValue", email));
        }
    }

    public void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new UserValidationException("Name cannot be null or empty", Map.of("field", "name"));
        }
    }

    public void validateEnrolledAt(Integer enrolledAt) {
        if (enrolledAt == null) {
            throw new UserValidationException("Enrolled year cannot be null", Map.of("field", "enrolledAt"));
        }
        if (enrolledAt < 1900 || enrolledAt > 2100) {
            throw new UserValidationException("Invalid enrolled year: " + enrolledAt,
                    Map.of("field", "enrolledAt", "rejectedValue", enrolledAt));
        }
    }

    public void validateClassNo(Integer classNo) {
        if (classNo == null) {
            throw new UserValidationException("Class number cannot be null", Map.of("field", "classNo"));
        }
        if (classNo < 1) {
            throw new UserValidationException("Class number must be positive: " + classNo,
                    Map.of("field", "classNo", "rejectedValue", classNo));
        }
    }

    public void validateGrade(Integer grade) {
        if (grade == null) {
            throw new UserValidationException("Grade cannot be null", Map.of("field", "grade"));
        }
        if (grade < 1 || grade > 3) {
            throw new UserValidationException("Grade must be between 1 and 3: " + grade,
                    Map.of("field", "grade", "rejectedValue", grade));
        }
    }

    public record Target(String email, String name, Integer enrolledAt, Integer classNo, Integer grade) {
    }
}

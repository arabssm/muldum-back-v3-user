package co.kr.muldum.domain.model;

import co.kr.muldum.domain.validator.UserValidator;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserFactory {

    private final UserValidator userValidator;

    public UserFactory(UserValidator userValidator) {
        this.userValidator = userValidator;
    }

    public User create(UserCreateCommand command) {
        userValidator.validate(new UserValidator.Target(
                command.email(),
                command.name(),
                command.enrolledAt(),
                command.classNo(),
                command.grade()
        ));

        return new User(
                command.id(),
                command.name(),
                command.enrolledAt(),
                command.email(),
                command.classNo(),
                command.grade(),
                command.userRole(),
                command.teamId()
        );
    }

    public record UserCreateCommand(UUID id,
                                    String name,
                                    Integer enrolledAt,
                                    String email,
                                    Integer classNo,
                                    Integer grade,
                                    Role userRole,
                                    Long teamId) {
    }
}

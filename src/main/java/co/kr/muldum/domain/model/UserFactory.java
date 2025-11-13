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
                command.name()
        ));

        return new User(
                command.id(),
                command.email(),
                command.name(),
                command.profile(),
                command.userType()
        );
    }

    public record UserCreateCommand(UUID id,
                                    String email,
                                    String name,
                                    UserProfile profile,
                                    UserType userType) {
    }
}

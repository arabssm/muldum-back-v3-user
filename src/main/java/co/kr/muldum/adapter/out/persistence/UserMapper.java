package co.kr.muldum.adapter.out.persistence;

import co.kr.muldum.domain.model.User;
import co.kr.muldum.domain.model.UserFactory;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final UserFactory userFactory;

    public UserMapper(UserFactory userFactory) {
        this.userFactory = userFactory;
    }

    public User toDomain(UserJpaEntity entity, Long teamId) {
        return userFactory.create(new UserFactory.UserCreateCommand(
                entity.getId(),
                entity.getName(),
                entity.getEnrolledAt(),
                entity.getEmail(),
                entity.getClassNo(),
                entity.getGrade(),
                entity.getUserRole(),
                teamId
        ));
    }

    public UserJpaEntity toEntity(User user) {
        return new UserJpaEntity(
                user.getId(),
                user.getName(),
                user.getEnrolledAt(),
                user.getEmail(),
                user.getClassNo(),
                user.getGrade(),
                user.getUserRole()
        );
    }
}

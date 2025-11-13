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

    public User toDomain(UserJpaEntity entity) {
        return userFactory.create(new UserFactory.UserCreateCommand(
                entity.getId(),
                entity.getEmail(),
                entity.getName(),
                entity.getProfile(),
                entity.getUserType()
        ));
    }

    public UserJpaEntity toEntity(User user) {
        return UserJpaEntity.of(
                user.getEmail(),
                user.getName(),
                user.getProfile(),
                user.getUserType()
        );
    }
}

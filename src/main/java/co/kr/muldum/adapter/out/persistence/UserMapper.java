package co.kr.muldum.adapter.out.persistence;

import co.kr.muldum.domain.model.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper {

    public static User toDomain(UserJpaEntity entity, Long teamId) {
        return User.of(
                entity.getId(),
                entity.getName(),
                entity.getEnrolledAt(),
                entity.getEmail(),
                entity.getClassNo(),
                entity.getGrade(),
                entity.getUserRole(),
                teamId
        );
    }

    public static UserJpaEntity toEntity(User user) {
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

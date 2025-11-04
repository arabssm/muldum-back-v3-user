package co.kr.muldum.adapter.out.persistence;

import co.kr.muldum.domain.model.UserLog;

public class UserLogMapper {

    public static UserLog toDomain(UserLogJpaEntity entity) {
        return UserLog.of(
                entity.getId(),
                entity.getStatus(),
                entity.getLoggedBy(),
                entity.getLoggedAt(),
                entity.getTitle(),
                entity.getContent(),
                entity.getMethod()
        );
    }

    public static UserLogJpaEntity toEntity(UserLog userLog) {
        return UserLogJpaEntity.of(
                userLog.getStatus(),
                userLog.getLoggedBy(),
                userLog.getTitle(),
                userLog.getContent(),
                userLog.getMethod()
        );
    }
}
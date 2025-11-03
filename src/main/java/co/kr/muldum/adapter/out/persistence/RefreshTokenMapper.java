package co.kr.muldum.adapter.out.persistence;

import co.kr.muldum.domain.model.RefreshToken;

public class RefreshTokenMapper {

    public static RefreshToken toDomain(RefreshTokenJpaEntity entity) {
        return RefreshToken.of(
                entity.getToken(),
                entity.getUserId(),
                entity.getExpiryDate()
        );
    }

    public static RefreshTokenJpaEntity toEntity(RefreshToken refreshToken) {
        return RefreshTokenJpaEntity.of(
                refreshToken.getToken(),
                refreshToken.getUserId(),
                refreshToken.getExpiryDate()
        );
    }
}
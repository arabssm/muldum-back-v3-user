package co.kr.muldum.adapter.out.persistence;

import co.kr.muldum.domain.model.RefreshToken;
import co.kr.muldum.domain.model.RefreshTokenFactory;
import org.springframework.stereotype.Component;

@Component
public class RefreshTokenMapper {

    private final RefreshTokenFactory refreshTokenFactory;

    public RefreshTokenMapper(RefreshTokenFactory refreshTokenFactory) {
        this.refreshTokenFactory = refreshTokenFactory;
    }

    public RefreshToken toDomain(RefreshTokenJpaEntity entity) {
        return refreshTokenFactory.create(new RefreshTokenFactory.RefreshTokenCreateCommand(
                entity.getToken(),
                entity.getUserId(),
                entity.getExpiryDate()
        ));
    }

    public RefreshTokenJpaEntity toEntity(RefreshToken refreshToken) {
        return RefreshTokenJpaEntity.of(
                refreshToken.getToken(),
                refreshToken.getUserId(),
                refreshToken.getExpiryDate()
        );
    }
}

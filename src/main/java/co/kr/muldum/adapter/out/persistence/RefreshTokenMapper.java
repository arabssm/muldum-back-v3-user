package co.kr.muldum.adapter.out.persistence;

import co.kr.muldum.domain.model.RefreshToken;
import org.springframework.stereotype.Component;

@Component
public class RefreshTokenMapper {

    public RefreshToken toDomain(RefreshTokenJpaEntity entity) {
        return RefreshToken.from(
                entity.getEmail(),
                entity.getRefreshToken()
        );
    }

    public RefreshTokenJpaEntity toEntity(RefreshToken refreshToken) {
        return RefreshTokenJpaEntity.of(
                refreshToken.getEmail(),
                refreshToken.getRefreshToken()
        );
    }
}

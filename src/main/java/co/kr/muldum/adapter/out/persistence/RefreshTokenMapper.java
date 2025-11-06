package co.kr.muldum.adapter.out.persistence;

import co.kr.muldum.domain.model.RefreshToken;
import org.springframework.stereotype.Component;

import java.time.Clock;

@Component
public class RefreshTokenMapper {

    private final Clock clock;

    public RefreshTokenMapper(Clock clock) {
        this.clock = clock;
    }

    public RefreshToken toDomain(RefreshTokenJpaEntity entity) {
        return RefreshToken.from(
                entity.getToken(),
                entity.getUserId(),
                entity.getExpiryDate(),
                clock
        );
    }

    public RefreshTokenJpaEntity toEntity(RefreshToken refreshToken) {
        return RefreshTokenJpaEntity.of(
                refreshToken.getToken(),
                refreshToken.getUserId(),
                refreshToken.getExpiryDate()
        );
    }
}

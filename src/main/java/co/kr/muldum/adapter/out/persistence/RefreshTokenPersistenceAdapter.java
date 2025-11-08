package co.kr.muldum.adapter.out.persistence;

import co.kr.muldum.application.port.out.DeleteRefreshTokenPort;
import co.kr.muldum.application.port.out.LoadRefreshTokenPort;
import co.kr.muldum.application.port.out.SaveRefreshTokenPort;
import co.kr.muldum.domain.model.RefreshToken;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Component
public class RefreshTokenPersistenceAdapter implements SaveRefreshTokenPort, LoadRefreshTokenPort, DeleteRefreshTokenPort {

    private final RefreshTokenJpaRepository refreshTokenJpaRepository;
    private final RefreshTokenMapper refreshTokenMapper;
    private final Clock clock;

    public RefreshTokenPersistenceAdapter(RefreshTokenJpaRepository refreshTokenJpaRepository,
                                          RefreshTokenMapper refreshTokenMapper,
                                          Clock clock) {
        this.refreshTokenJpaRepository = refreshTokenJpaRepository;
        this.refreshTokenMapper = refreshTokenMapper;
        this.clock = clock;
    }

    @Override
    @Transactional
    public void save(RefreshToken refreshToken) {
        // 만료된 토큰 정리 후 저장
        refreshTokenJpaRepository.deleteExpiredTokens(LocalDateTime.now(clock));
        refreshTokenJpaRepository.findByUserId(refreshToken.getUserId())
                .ifPresent(refreshTokenJpaRepository::delete);

        RefreshTokenJpaEntity entity = refreshTokenMapper.toEntity(refreshToken);
        refreshTokenJpaRepository.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenJpaRepository.findByToken(token)
                .map(refreshTokenMapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RefreshToken> findByUserId(UUID userId) {
        return refreshTokenJpaRepository.findByUserId(userId)
                .map(refreshTokenMapper::toDomain);
    }

    @Override
    @Transactional
    public void deleteByUserId(UUID userId) {
        refreshTokenJpaRepository.deleteByUserId(userId);
    }

    @Override
    @Transactional
    public void deleteByToken(String token) {
        refreshTokenJpaRepository.deleteByToken(token);
    }
}

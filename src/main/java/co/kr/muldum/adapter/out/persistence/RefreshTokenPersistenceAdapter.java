package co.kr.muldum.adapter.out.persistence;

import co.kr.muldum.application.port.out.DeleteRefreshTokenPort;
import co.kr.muldum.application.port.out.LoadRefreshTokenPort;
import co.kr.muldum.application.port.out.SaveRefreshTokenPort;
import co.kr.muldum.domain.model.RefreshToken;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Component
public class RefreshTokenPersistenceAdapter implements SaveRefreshTokenPort, LoadRefreshTokenPort, DeleteRefreshTokenPort {

    private final RefreshTokenJpaRepository refreshTokenJpaRepository;

    public RefreshTokenPersistenceAdapter(RefreshTokenJpaRepository refreshTokenJpaRepository) {
        this.refreshTokenJpaRepository = refreshTokenJpaRepository;
    }

    @Override
    @Transactional
    public void save(RefreshToken refreshToken) {
        refreshTokenJpaRepository.findByUserId(refreshToken.getUserId())
                .ifPresent(refreshTokenJpaRepository::delete);

        RefreshTokenJpaEntity entity = RefreshTokenMapper.toEntity(refreshToken);
        refreshTokenJpaRepository.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenJpaRepository.findByToken(token)
                .map(RefreshTokenMapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RefreshToken> findByUserId(UUID userId) {
        return refreshTokenJpaRepository.findByUserId(userId)
                .map(RefreshTokenMapper::toDomain);
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
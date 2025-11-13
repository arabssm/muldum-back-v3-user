package co.kr.muldum.adapter.out.persistence;

import co.kr.muldum.application.port.out.DeleteRefreshTokenPort;
import co.kr.muldum.application.port.out.LoadRefreshTokenPort;
import co.kr.muldum.application.port.out.SaveRefreshTokenPort;
import co.kr.muldum.domain.model.RefreshToken;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class RefreshTokenPersistenceAdapter implements SaveRefreshTokenPort, LoadRefreshTokenPort, DeleteRefreshTokenPort {

    private final RefreshTokenJpaRepository refreshTokenJpaRepository;
    private final RefreshTokenMapper refreshTokenMapper;

    public RefreshTokenPersistenceAdapter(RefreshTokenJpaRepository refreshTokenJpaRepository,
                                          RefreshTokenMapper refreshTokenMapper) {
        this.refreshTokenJpaRepository = refreshTokenJpaRepository;
        this.refreshTokenMapper = refreshTokenMapper;
    }

    @Override
    @Transactional
    public void save(RefreshToken refreshToken) {
        refreshTokenJpaRepository.findByEmail(refreshToken.getEmail())
                .ifPresentOrElse(
                        existing -> existing.updateToken(refreshToken.getRefreshToken()),
                        () -> {
                            RefreshTokenJpaEntity entity = refreshTokenMapper.toEntity(refreshToken);
                            refreshTokenJpaRepository.save(entity);
                        }
                );
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RefreshToken> findByRefreshToken(String refreshToken) {
        return refreshTokenJpaRepository.findByRefreshToken(refreshToken)
                .map(refreshTokenMapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RefreshToken> findByEmail(String email) {
        return refreshTokenJpaRepository.findByEmail(email)
                .map(refreshTokenMapper::toDomain);
    }

    @Override
    @Transactional
    public void deleteByEmail(String email) {
        refreshTokenJpaRepository.deleteByEmail(email);
    }

    @Override
    @Transactional
    public void deleteByRefreshToken(String refreshToken) {
        refreshTokenJpaRepository.deleteByRefreshToken(refreshToken);
    }
}

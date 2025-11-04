package co.kr.muldum.adapter.out.persistence;

import co.kr.muldum.application.port.out.LoadUserPort;
import co.kr.muldum.domain.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class UserPersistenceAdapter implements LoadUserPort {

    private static final Logger log = LoggerFactory.getLogger(UserPersistenceAdapter.class);

    private final UserJpaRepository userJpaRepository;
    private final MemberJpaRepository memberJpaRepository;

    public UserPersistenceAdapter(UserJpaRepository userJpaRepository, MemberJpaRepository memberJpaRepository) {
        this.userJpaRepository = userJpaRepository;
        this.memberJpaRepository = memberJpaRepository;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try {
            Optional<UserJpaEntity> userEntity = userJpaRepository.findByEmailIgnoreCase(email);

            if (userEntity.isEmpty()) {
                return Optional.empty();
            }

            UserJpaEntity entity = userEntity.get();

            Long teamId = memberJpaRepository.findByUserId(entity.getId())
                    .map(MemberJpaEntity::getTeamId)
                    .orElse(null);

            return Optional.of(UserMapper.toDomain(entity, teamId));
        } catch (Exception e) {
            log.error("DB 조회 중 예외 발생!", e);
            throw e;
        }
    }

    @Override
    public Optional<User> findById(UUID id) {
        try {
            Optional<UserJpaEntity> userEntity = userJpaRepository.findById(id);

            if (userEntity.isEmpty()) {
                return Optional.empty();
            }

            UserJpaEntity entity = userEntity.get();

            Long teamId = memberJpaRepository.findByUserId(entity.getId())
                    .map(MemberJpaEntity::getTeamId)
                    .orElse(null);

            return Optional.of(UserMapper.toDomain(entity, teamId));
        } catch (Exception e) {
            log.error("DB 조회 중 예외 발생!", e);
            throw e;
        }
    }
}

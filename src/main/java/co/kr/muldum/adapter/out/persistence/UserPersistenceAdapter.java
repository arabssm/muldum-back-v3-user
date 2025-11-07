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

    public UserPersistenceAdapter(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try {
            return userJpaRepository.findByEmailIgnoreCaseWithMember(email)
                    .map(UserMapper::toDomain);
        } catch (Exception e) {
            log.error("DB 조회 중 예외 발생!", e);
            throw e;
        }
    }

    @Override
    public Optional<User> findById(UUID id) {
        try {
            return userJpaRepository.findByIdWithMember(id)
                    .map(UserMapper::toDomain);
        } catch (Exception e) {
            log.error("DB 조회 중 예외 발생!", e);
            throw e;
        }
    }
}

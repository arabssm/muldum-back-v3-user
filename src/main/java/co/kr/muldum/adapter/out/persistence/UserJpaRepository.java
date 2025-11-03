package co.kr.muldum.adapter.out.persistence;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserJpaEntity, Long> {
    Optional<UserJpaEntity> findByEmailIgnoreCase(String email);
    Optional<UserJpaEntity> findAllByEmailIgnoreCase(String email);
}

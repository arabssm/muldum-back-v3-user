package co.kr.muldum.adapter.out.persistence;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserJpaRepository extends JpaRepository<UserJpaEntity, UUID> {
    Optional<UserJpaEntity> findByEmailIgnoreCase(String email);
    Optional<UserJpaEntity> findAllByEmailIgnoreCase(String email);

    @Query("SELECT u FROM UserJpaEntity u LEFT JOIN FETCH u.member WHERE LOWER(u.email) = LOWER(:email)")
    Optional<UserJpaEntity> findByEmailIgnoreCaseWithMember(@Param("email") String email);

    @Query("SELECT u FROM UserJpaEntity u LEFT JOIN FETCH u.member WHERE u.id = :id")
    Optional<UserJpaEntity> findByIdWithMember(@Param("id") UUID id);
}

package co.kr.muldum.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MemberJpaRepository extends JpaRepository<MemberJpaEntity, Long> {
    Optional<MemberJpaEntity> findByStudentId(UUID studentId);
    List<MemberJpaEntity> findByTeamId(Long teamId);
    Optional<MemberJpaEntity> findByTeamIdAndStudentId(Long teamId, UUID studentId);
}

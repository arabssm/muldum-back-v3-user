package co.kr.muldum.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserLogJpaRepository extends JpaRepository<UserLogJpaEntity, Long> {

    List<UserLogJpaEntity> findAllByOrderByLoggedAtDesc();

    List<UserLogJpaEntity> findByMethodOrderByLoggedAtDesc(String method);
}
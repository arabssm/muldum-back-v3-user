package co.kr.muldum.application.port.out;

import co.kr.muldum.domain.model.User;

import java.util.Optional;

public interface LoadUserPort {
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);
}

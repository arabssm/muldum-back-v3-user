package co.kr.muldum.adapter.out.persistence;

import co.kr.muldum.application.port.out.LoadUserLogPort;
import co.kr.muldum.domain.model.UserLog;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserLogPersistenceAdapter implements LoadUserLogPort {

    private final UserLogJpaRepository userLogJpaRepository;

    public UserLogPersistenceAdapter(UserLogJpaRepository userLogJpaRepository) {
        this.userLogJpaRepository = userLogJpaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserLog> findAll() {
        return userLogJpaRepository.findAllByOrderByLoggedAtDesc()
                .stream()
                .map(UserLogMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserLog> findByMethod(String method) {
        return userLogJpaRepository.findByMethodOrderByLoggedAtDesc(method)
                .stream()
                .map(UserLogMapper::toDomain)
                .collect(Collectors.toList())    ;
    }
}
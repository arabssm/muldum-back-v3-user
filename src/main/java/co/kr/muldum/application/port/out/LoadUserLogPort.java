package co.kr.muldum.application.port.out;

import co.kr.muldum.domain.model.UserLog;

import java.util.List;

public interface LoadUserLogPort {
    List<UserLog> findAll();
    List<UserLog> findByMethod(String method);
}
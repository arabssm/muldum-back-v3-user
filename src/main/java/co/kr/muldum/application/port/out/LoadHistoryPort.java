package co.kr.muldum.application.port.out;

import co.kr.muldum.domain.model.History;

import java.util.List;

public interface LoadHistoryPort {
    List<History> findByGeneration(Integer generation);

    Integer findMaxGeneration();
}
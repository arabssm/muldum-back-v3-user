package co.kr.muldum.application.port.in;

import co.kr.muldum.domain.model.History;

import java.util.List;

public interface GetHistoryUseCase {
    List<History> getHistoryList(Integer generation);
}
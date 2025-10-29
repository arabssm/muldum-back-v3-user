package co.kr.muldum.application.port.in;

import co.kr.muldum.application.port.in.response.HistoryListResponse;

public interface GetHistoryUseCase {
    HistoryListResponse getHistoryList(Integer generation);
}
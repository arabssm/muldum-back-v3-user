package co.kr.muldum.application.port.in.response;

import co.kr.muldum.domain.model.History;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class HistoryListResponse {
    private final List<History> histories;

    public static HistoryListResponse from(List<History> histories) {
        return new HistoryListResponse(histories);
    }
}
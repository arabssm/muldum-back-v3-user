package co.kr.muldum.application.service;

import co.kr.muldum.application.port.in.GetHistoryUseCase;
import co.kr.muldum.application.port.out.LoadHistoryPort;
import co.kr.muldum.domain.exception.HistoryNotFoundException;
import co.kr.muldum.domain.exception.InvalidGenerationException;
import co.kr.muldum.domain.model.History;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class HistoryQueryService implements GetHistoryUseCase {

    private final LoadHistoryPort loadHistoryPort;

    public HistoryQueryService(LoadHistoryPort loadHistoryPort) {
        this.loadHistoryPort = loadHistoryPort;
    }

    @Override
    public List<History> getHistoryList(Integer generation) {
        // generation이 명시적으로 제공된 경우
        if (generation != null) {
            if (generation <= 0) {
                throw new InvalidGenerationException(generation);
            }

            List<History> historyList = loadHistoryPort.findByGeneration(generation);
            if (historyList.isEmpty()) {
                throw new HistoryNotFoundException(generation);
            }
            return historyList;
        }

        // generation이 제공되지 않은 경우 - 최신 세대 조회
        Integer maxGeneration = loadHistoryPort.findMaxGeneration();
        if (maxGeneration == null) {
            return List.of();
        }

        return loadHistoryPort.findByGeneration(maxGeneration);
    }
}
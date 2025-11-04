package co.kr.muldum.application.service;

import co.kr.muldum.application.port.in.GetUserLogsUseCase;
import co.kr.muldum.application.port.in.command.GetUserLogsCommand;
import co.kr.muldum.application.port.out.LoadUserLogPort;
import co.kr.muldum.domain.model.UserLog;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class GetUserLogsService implements GetUserLogsUseCase {

    private final LoadUserLogPort loadUserLogPort;

    public GetUserLogsService(LoadUserLogPort loadUserLogPort) {
        this.loadUserLogPort = loadUserLogPort;
    }

    @Override
    public List<UserLog> getLogs(GetUserLogsCommand command) {
        return command.getMethod()
                .map(loadUserLogPort::findByMethod)
                .orElseGet(loadUserLogPort::findAll);
    }
}
package co.kr.muldum.application.port.in;

import co.kr.muldum.application.port.in.command.GetUserLogsCommand;
import co.kr.muldum.domain.model.UserLog;

import java.util.List;

public interface GetUserLogsUseCase {
    List<UserLog> getLogs(GetUserLogsCommand command);
}
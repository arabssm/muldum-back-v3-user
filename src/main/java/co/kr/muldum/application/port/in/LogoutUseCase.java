package co.kr.muldum.application.port.in;

import co.kr.muldum.application.port.in.command.LogoutCommand;

public interface LogoutUseCase {
    void logout(LogoutCommand command);
}
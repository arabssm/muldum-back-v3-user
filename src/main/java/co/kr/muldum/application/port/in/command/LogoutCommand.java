package co.kr.muldum.application.port.in.command;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class LogoutCommand {
    private final String refreshToken;

    public static LogoutCommand of(String refreshToken) {
        return new LogoutCommand(refreshToken);
    }
}
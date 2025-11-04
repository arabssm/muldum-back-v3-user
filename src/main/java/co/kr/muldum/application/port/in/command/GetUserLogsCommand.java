package co.kr.muldum.application.port.in.command;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class GetUserLogsCommand {
    private final String method;

    public static GetUserLogsCommand of(String method) {
        return new GetUserLogsCommand(method);
    }

    public Optional<String> getMethod() {
        return Optional.ofNullable(method);
    }
}
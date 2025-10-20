package co.kr.muldum.application.port.in.command;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class GoogleLoginCommand {
    private final String authorizationCode;

    public static GoogleLoginCommand of(String authorizationCode) {
        validateAuthorizationCode(authorizationCode);
        return new GoogleLoginCommand(authorizationCode);
    }

    private static void validateAuthorizationCode(String authorizationCode) {
        if (authorizationCode == null || authorizationCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Authorization code cannot be null or empty");
        }
    }
}

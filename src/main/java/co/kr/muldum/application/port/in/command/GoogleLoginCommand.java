package co.kr.muldum.application.port.in.command;

public class GoogleLoginCommand {
    private final String authorizationCode;

    private GoogleLoginCommand(String authorizationCode) {
        if (authorizationCode == null || authorizationCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Authorization code cannot be null or empty");
        }
        this.authorizationCode = authorizationCode;
    }

    public static GoogleLoginCommand of(String authorizationCode) {
        return new GoogleLoginCommand(authorizationCode);
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }
}
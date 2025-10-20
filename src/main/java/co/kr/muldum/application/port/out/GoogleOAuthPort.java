package co.kr.muldum.application.port.out;

public interface GoogleOAuthPort {
    String getEmailFromAuthCode(String authorizationCode);
}

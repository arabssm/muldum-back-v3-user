package co.kr.muldum.adapter.out.oauth;

import co.kr.muldum.application.port.out.GoogleOAuthPort;
import co.kr.muldum.global.exception.InvalidAuthorizationCodeException;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;

@Component
public class GoogleOAuthAdapter implements GoogleOAuthPort {

    private final String clientId;
    private final String clientSecret;
    private final String redirectUri;
    private final String userInfoUri;
    private final RestTemplate restTemplate;

    public GoogleOAuthAdapter(
            @Value("${google.oauth.client-id}") String clientId,
            @Value("${google.oauth.client-secret}") String clientSecret,
            @Value("${google.oauth.redirect-uri}") String redirectUri,
            @Value("${google.oauth.user-info-uri}") String userInfoUri) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
        this.userInfoUri = userInfoUri;
        this.restTemplate = new RestTemplate();
    }

    @Override
    public String getEmailFromAuthCode(String authorizationCode) {
        try {
            GoogleTokenResponse tokenResponse = new GoogleAuthorizationCodeTokenRequest(
                    new NetHttpTransport(),
                    new GsonFactory(),
                    clientId,
                    clientSecret,
                    authorizationCode,
                    redirectUri
            ).execute();

            String accessToken = tokenResponse.getAccessToken();

            return getUserEmail(accessToken);

        } catch (IOException e) {
            String errorMessage = String.format(
                "Google OAuth 토큰 교환 실패 - redirect_uri: %s, error: %s",
                redirectUri,
                e.getMessage()
            );
            throw new InvalidAuthorizationCodeException(errorMessage, e);
        }
    }

    private String getUserEmail(String accessToken) {
        try {
            Map<String, Object> userInfo = restTemplate.getForObject(
                    userInfoUri + "?access_token=" + accessToken,
                    Map.class
            );

            if (userInfo == null || !userInfo.containsKey("email")) {
                throw new InvalidAuthorizationCodeException("사용자 정보를 가져올 수 없습니다");
            }

            return (String) userInfo.get("email");
        } catch (Exception e) {
            throw new InvalidAuthorizationCodeException("사용자 정보를 가져올 수 없습니다", e);
        }
    }
}

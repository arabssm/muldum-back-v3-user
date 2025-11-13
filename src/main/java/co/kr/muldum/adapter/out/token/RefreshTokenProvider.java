package co.kr.muldum.adapter.out.token;

import co.kr.muldum.application.port.out.RefreshTokenPort;
import co.kr.muldum.domain.model.RefreshToken;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RefreshTokenProvider implements RefreshTokenPort {

    @Override
    public RefreshToken generateRefreshToken(String email) {
        String token = UUID.randomUUID().toString();
        return RefreshToken.createNew(email, token);
    }
}

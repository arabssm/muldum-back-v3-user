package co.kr.muldum.application.port.in;

import co.kr.muldum.application.port.in.command.RefreshTokenCommand;
import co.kr.muldum.application.port.in.response.RefreshTokenResponse;

public interface RefreshAccessTokenUseCase {
    RefreshTokenResponse refresh(RefreshTokenCommand command);
}
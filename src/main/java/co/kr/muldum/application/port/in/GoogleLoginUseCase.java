package co.kr.muldum.application.port.in;

import co.kr.muldum.application.port.in.command.GoogleLoginCommand;
import co.kr.muldum.application.port.in.response.LoginResponse;

public interface GoogleLoginUseCase {
    LoginResponse login(GoogleLoginCommand command);
}

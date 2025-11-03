package co.kr.muldum.adapter.in.web.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class LogoutResponseDto {
    private final String message;

    public static LogoutResponseDto of(String message) {
        return new LogoutResponseDto(message);
    }
}
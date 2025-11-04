package co.kr.muldum.adapter.in.web.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class LogContentsDto {
    private final String title;
    private final String content;

    public static LogContentsDto of(String title, String content) {
        return new LogContentsDto(title, content);
    }
}
package co.kr.muldum.adapter.in.web.dto;

import co.kr.muldum.domain.model.LogStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class UserLogResponseDto {
    private final LogStatus status;

    @JsonProperty("logged_by")
    private final String loggedBy;

    @JsonProperty("logged_at")
    private final LocalDateTime loggedAt;

    @JsonProperty("log_contents")
    private final LogContentsDto logContents;

    public static UserLogResponseDto of(LogStatus status, String loggedBy,
                                       LocalDateTime loggedAt, LogContentsDto logContents) {
        return new UserLogResponseDto(status, loggedBy, loggedAt, logContents);
    }
}
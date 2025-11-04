package co.kr.muldum.domain.model;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class UserLog {
    private final Long id;
    private final LogStatus status;
    private final UUID loggedBy;
    private final LocalDateTime loggedAt;
    private final String title;
    private final String content;
    private final String method;

    private UserLog(Long id, LogStatus status, UUID loggedBy, LocalDateTime loggedAt,
                   String title, String content, String method) {
        this.id = id;
        this.status = status;
        this.loggedBy = loggedBy;
        this.loggedAt = loggedAt;
        this.title = title;
        this.content = content;
        this.method = method;
    }

    public static UserLog of(Long id, LogStatus status, UUID loggedBy, LocalDateTime loggedAt,
                            String title, String content, String method) {
        return new UserLog(id, status, loggedBy, loggedAt, title, content, method);
    }
}
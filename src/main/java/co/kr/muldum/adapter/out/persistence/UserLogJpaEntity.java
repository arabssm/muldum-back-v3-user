package co.kr.muldum.adapter.out.persistence;

import co.kr.muldum.domain.model.LogStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "user_log")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserLogJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LogStatus status;

    @Column(nullable = false, name = "logged_by")
    private UUID loggedBy;

    @Column(nullable = false, name = "logged_at")
    private LocalDateTime loggedAt;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private String method;

    private UserLogJpaEntity(LogStatus status, UUID loggedBy, String title, String content, String method) {
        this.status = status;
        this.loggedBy = loggedBy;
        this.loggedAt = LocalDateTime.now();
        this.title = title;
        this.content = content;
        this.method = method;
    }

    public static UserLogJpaEntity of(LogStatus status, UUID loggedBy, String title, String content, String method) {
        return new UserLogJpaEntity(status, loggedBy, title, content, method);
    }
}
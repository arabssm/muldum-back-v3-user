package co.kr.muldum.infrastructure.messaging.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserEventMessage implements Serializable {
    private UUID userId;
    private String email;
    private String eventType;
    private LocalDateTime occurredAt;
}

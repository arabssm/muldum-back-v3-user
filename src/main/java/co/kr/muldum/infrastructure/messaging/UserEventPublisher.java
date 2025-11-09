package co.kr.muldum.infrastructure.messaging;

import co.kr.muldum.domain.model.User;
import co.kr.muldum.infrastructure.config.RabbitMQConfig;
import co.kr.muldum.infrastructure.messaging.dto.UserEventMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserEventPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final Clock clock = Clock.systemUTC();

    public void publishLoginEvent(User user) {
        publishEvent(user, "USER_LOGIN");
    }

    private void publishEvent(User user, String eventType) {
        try {
            UserEventMessage message = new UserEventMessage(
                    user.getId(),
                    user.getEmail(),
                    eventType,
                    LocalDateTime.now(clock)
            );

            rabbitTemplate.convertAndSend(
                    RabbitMQConfig.USER_EVENT_EXCHANGE,
                    RabbitMQConfig.USER_EVENT_ROUTING_KEY,
                    message
            );
            log.info("User event published: type={}, userId={}", eventType, user.getId());
        } catch (Exception e) {
            log.warn("Failed to publish user event: type={}, userId={}", eventType, user.getId(), e);
        }
    }
}

package co.kr.muldum.domain.model;

import co.kr.muldum.domain.exception.InvalidRefreshTokenException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class RefreshTokenTest {

    private static final Clock FIXED_UTC_CLOCK = Clock.fixed(Instant.parse("2024-01-01T12:00:00Z"), ZoneId.of("UTC"));

    @Test
    @DisplayName("새 리프레시 토큰은 만료 시간이 미래여야 한다")
    void createNewTokenRequiresFutureExpiry() {
        // Clock의 ZoneId와 동일한 시간대를 사용해야 함
        LocalDateTime futureExpiry = LocalDateTime.now(FIXED_UTC_CLOCK).plusSeconds(3600);

        RefreshToken token = RefreshToken.createNew("token", UUID.randomUUID(), futureExpiry, FIXED_UTC_CLOCK);

        assertFalse(token.isExpired(FIXED_UTC_CLOCK));
    }

    @Test
    @DisplayName("현재 시간과 비교해 만료된 토큰을 감지한다")
    void detectsExpiredToken() {
        LocalDateTime pastExpiry = LocalDateTime.ofInstant(FIXED_UTC_CLOCK.instant().minusSeconds(60), ZoneId.of("UTC"));
        RefreshToken token = RefreshToken.from("token", UUID.randomUUID(), pastExpiry, FIXED_UTC_CLOCK);

        assertTrue(token.isExpired(FIXED_UTC_CLOCK));
    }

    @Test
    @DisplayName("만료 시간이 과거인 경우 생성 시 예외를 발생시킨다")
    void createNewTokenWithPastExpiryThrows() {
        LocalDateTime pastExpiry = LocalDateTime.ofInstant(FIXED_UTC_CLOCK.instant().minusSeconds(5), ZoneId.of("UTC"));

        assertThrows(InvalidRefreshTokenException.class,
                () -> RefreshToken.createNew("token", UUID.randomUUID(), pastExpiry, FIXED_UTC_CLOCK));
    }
}

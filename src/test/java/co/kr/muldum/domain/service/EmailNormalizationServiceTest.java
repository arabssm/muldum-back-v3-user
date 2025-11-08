package co.kr.muldum.domain.service;

import co.kr.muldum.domain.exception.InvalidEmailException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailNormalizationServiceTest {

    private final EmailNormalizationService service = new EmailNormalizationService();

    @Test
    @DisplayName("이메일을 소문자로 정규화하고 공백을 제거한다")
    void normalizeEmailSuccessfully() {
        String normalized = service.normalize("  USER@Example.COM  ");

        assertEquals("user@example.com", normalized);
    }

    @Test
    @DisplayName("국제화 문자를 포함한 이메일도 안정적으로 소문자 변환된다")
    void normalizeInternationalEmail() {
        String normalized = service.normalize("ÜSER@EXAMPLE.CO.KR");

        assertEquals("üser@example.co.kr", normalized);
    }

    @Test
    @DisplayName("null 또는 공백 이메일은 InvalidEmailException을 던진다")
    void invalidEmailThrowsException() {
        assertThrows(InvalidEmailException.class, () -> service.normalize("   "));
        assertThrows(InvalidEmailException.class, () -> service.normalize(null));
    }
}

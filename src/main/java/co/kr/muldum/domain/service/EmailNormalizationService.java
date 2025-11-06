package co.kr.muldum.domain.service;

import co.kr.muldum.domain.exception.InvalidEmailException;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * 이메일 정규화를 담당하는 도메인 서비스.
 * - 앞뒤 공백 제거
 * - 소문자 변환
 */
@Service
public class EmailNormalizationService {

    /**
     * 입력 이메일을 정규화합니다.
     *
     * @param email 원본 이메일
     * @return 정규화된 이메일
     * @throws InvalidEmailException 이메일이 null/blank 인 경우
     */
    public String normalize(String email) {
        if (email == null || email.isBlank()) {
            throw new InvalidEmailException("Email cannot be null or empty");
        }

        var trimmed = email.trim();
        if (trimmed.isEmpty()) {
            throw new InvalidEmailException("Email cannot contain only whitespace");
        }

        return trimmed.toLowerCase(Locale.ROOT);
    }
}

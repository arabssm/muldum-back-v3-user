package co.kr.muldum.domain.service;

import java.util.Locale;

/**
 * 이메일 정규화를 담당하는 도메인 서비스
 * 비즈니스 로직: 이메일을 소문자로 변환하고 공백을 제거
 */
public class EmailNormalizationService {

    private EmailNormalizationService() {
        // Utility class - prevent instantiation
    }

    /**
     * 이메일을 정규화합니다.
     * - 앞뒤 공백 제거
     * - 소문자로 변환
     *
     * @param email 원본 이메일
     * @return 정규화된 이메일, 입력이 null이면 null 반환
     */
    public static String normalize(String email) {
        if (email == null) {
            return null;
        }
        return email.trim().toLowerCase(Locale.ROOT);
    }
}
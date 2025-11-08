package co.kr.muldum.domain.validator;

/**
 * 도메인 객체 생성을 위한 검증 계약을 정의합니다.
 *
 * @param <T> 검증 대상 타입
 */
public interface DomainValidator<T> {

    void validate(T target);
}

package co.kr.muldum.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class Award {
    private final Long awardId;
    private final AwardType awardType;
    private final LocalDate givenAt;

    public static Award of(Long awardId, AwardType awardType, LocalDate givenAt) {
        return new Award(awardId, awardType, givenAt);
    }
}
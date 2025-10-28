package co.kr.muldum.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class Award {
    private final Long awardId;
    private final String awardType;
    private final LocalDate givenAt;

    public static Award of(Long awardId, String awardType, LocalDate givenAt) {
        return new Award(awardId, awardType, givenAt);
    }
}
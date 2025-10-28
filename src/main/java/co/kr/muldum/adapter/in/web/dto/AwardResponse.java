package co.kr.muldum.adapter.in.web.dto;

import co.kr.muldum.domain.model.Award;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class AwardResponse {

    private final String awardType;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private final LocalDate givenAt;

    private AwardResponse(String awardType, LocalDate givenAt) {
        this.awardType = awardType;
        this.givenAt = givenAt;
    }

    public static AwardResponse from(Award award) {
        return new AwardResponse(
                award.getAwardType(),
                award.getGivenAt()
        );
    }
}
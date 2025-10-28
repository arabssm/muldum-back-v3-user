package co.kr.muldum.adapter.out.persistence;

import co.kr.muldum.domain.model.Award;
import co.kr.muldum.domain.model.History;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HistoryMapper {

    public static History toDomain(HistoryJpaEntity entity) {
        List<Award> awards = entity.getAwards().stream()
                .map(HistoryMapper::awardToDomain)
                .collect(Collectors.toList());

        return History.of(
                entity.getId(),
                entity.getName(),
                entity.getGeneration(),
                entity.getClubType(),
                entity.getDescription(),
                entity.getLogoUrl(),
                awards
        );
    }

    private static Award awardToDomain(AwardJpaEntity entity) {
        return Award.of(
                entity.getAwardId(),
                entity.getAwardType(),
                entity.getGivenAt()
        );
    }
}
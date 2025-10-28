package co.kr.muldum.adapter.in.web.dto;

import co.kr.muldum.domain.model.ClubType;
import co.kr.muldum.domain.model.History;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class HistoryResponse {

    private final Long id;
    private final String name;
    private final Integer generation;
    private final String clubType;
    private final String description;
    private final String logoUrl;
    private final List<AwardResponse> awards;

    private HistoryResponse(Long id, String name, Integer generation, String clubType,
                             String description, String logoUrl, List<AwardResponse> awards) {
        this.id = id;
        this.name = name;
        this.generation = generation;
        this.clubType = clubType;
        this.description = description;
        this.logoUrl = logoUrl;
        this.awards = awards;
    }

    public static HistoryResponse from(History history) {
        List<AwardResponse> awardResponses = history.getAwards().stream()
                .map(AwardResponse::from)
                .collect(Collectors.toList());

        return new HistoryResponse(
                history.getId(),
                history.getName(),
                history.getGeneration(),
                history.getClubType().name().toLowerCase(),
                history.getDescription(),
                history.getLogoUrl(),
                awardResponses
        );
    }
}
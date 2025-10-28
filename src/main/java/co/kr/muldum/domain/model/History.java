package co.kr.muldum.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class History {
    private final Long id;
    private final String name;
    private final Integer generation;
    private final ClubType clubType;
    private final String description;
    private final String logoUrl;
    private final List<Award> awards;

    public static History of(Long id, String name, Integer generation, ClubType clubType,
                              String description, String logoUrl, List<Award> awards) {
        return new History(id, name, generation, clubType, description, logoUrl, awards);
    }
}
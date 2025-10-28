package co.kr.muldum.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "awards")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AwardJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "award_id")
    private Long awardId;

    @Column(name = "award_type", nullable = false)
    private String awardType;

    @Column(name = "given_at", nullable = false)
    private LocalDate givenAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "history_id", nullable = false)
    private HistoryJpaEntity history;

    public AwardJpaEntity(Long awardId, String awardType, LocalDate givenAt, HistoryJpaEntity history) {
        this.awardId = awardId;
        this.awardType = awardType;
        this.givenAt = givenAt;
        this.history = history;
    }
}
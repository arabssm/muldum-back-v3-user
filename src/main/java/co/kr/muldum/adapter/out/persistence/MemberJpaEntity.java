package co.kr.muldum.adapter.out.persistence;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "\"member\"")
public class MemberJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "team_id", nullable = false)
    private Long teamId;

    protected MemberJpaEntity() {
    }

    public MemberJpaEntity(Long id, UUID userId, Long teamId) {
        this.id = id;
        this.userId = userId;
        this.teamId = teamId;
    }

    public Long getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public Long getTeamId() {
        return teamId;
    }
}

package co.kr.muldum.adapter.out.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "\"member\"")
public class MemberJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "team_id", nullable = false)
    private Long teamId;

    protected MemberJpaEntity() {
    }

    public MemberJpaEntity(Long id, Long userId, Long teamId) {
        this.id = id;
        this.userId = userId;
        this.teamId = teamId;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getTeamId() {
        return teamId;
    }
}

package co.kr.muldum.adapter.out.persistence;

import co.kr.muldum.domain.model.UserType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("STUDENT")
public class StudentJpaEntity extends UserJpaEntity {

    @Column(name = "team_id")
    private Long teamId;

    protected StudentJpaEntity() {
    }

    public StudentJpaEntity(Long userId, String email, String name, Long teamId) {
        super(userId, email, name, UserType.STUDENT);
        this.teamId = teamId;
    }

    public Long getTeamId() {
        return teamId;
    }
}
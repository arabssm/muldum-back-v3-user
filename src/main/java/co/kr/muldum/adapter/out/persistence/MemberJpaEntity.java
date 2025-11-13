package co.kr.muldum.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
    name = "members",
    indexes = {
        @Index(name = "idx_members_team_id", columnList = "team_id"),
        @Index(name = "idx_members_student_id", columnList = "student_id")
    },
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_members_team_student", columnNames = {"team_id", "student_id"})
    }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "team_id", nullable = false)
    private Long teamId;

    @Column(name = "student_id", nullable = false)
    private UUID studentId;

    @Column(nullable = false)
    private String role;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    protected MemberJpaEntity(Long teamId, UUID studentId, String role) {
        this.teamId = teamId;
        this.studentId = studentId;
        this.role = role;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public static MemberJpaEntity of(Long teamId, UUID studentId, String role) {
        return new MemberJpaEntity(teamId, studentId, role);
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}

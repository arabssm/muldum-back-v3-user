package co.kr.muldum.adapter.out.persistence;

import co.kr.muldum.domain.model.Role;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "\"user\"")
public class UserJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(nullable = false, length = 4)
    private String name;

    @Column(name = "enrolled_at", nullable = false)
    private Integer enrolledAt;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "class_no", nullable = false)
    private Integer classNo;

    @Column(nullable = false)
    private Integer grade;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role", nullable = false)
    private Role userRole;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private MemberJpaEntity member;

    protected UserJpaEntity() {
    }

    public UserJpaEntity(UUID id, String name, Integer enrolledAt, String email,
                         Integer classNo, Integer grade, Role userRole) {
        this.id = id;
        this.name = name;
        this.enrolledAt = enrolledAt;
        this.email = email;
        this.classNo = classNo;
        this.grade = grade;
        this.userRole = userRole;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getEnrolledAt() {
        return enrolledAt;
    }

    public String getEmail() {
        return email;
    }

    public Integer getClassNo() {
        return classNo;
    }

    public Integer getGrade() {
        return grade;
    }

    public Role getUserRole() {
        return userRole;
    }

    public MemberJpaEntity getMember() {
        return member;
    }
}

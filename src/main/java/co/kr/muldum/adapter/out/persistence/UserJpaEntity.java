package co.kr.muldum.adapter.out.persistence;

import co.kr.muldum.domain.model.UserType;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type")
public abstract class UserJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", insertable = false, updatable = false)
    private UserType userType;

    protected UserJpaEntity() {
    }

    protected UserJpaEntity(Long userId, String email, String name, UserType userType) {
        this.userId = userId;
        this.email = email;
        this.name = name;
        this.userType = userType;
    }

    public Long getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public UserType getUserType() {
        return userType;
    }
}
package co.kr.muldum.adapter.out.persistence;

import co.kr.muldum.domain.model.UserProfile;
import co.kr.muldum.domain.model.UserType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private UserProfile profile;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", length = 255)
    private UserType userType;

    protected UserJpaEntity(String email, String name, UserProfile profile, UserType userType) {
        this.email = email;
        this.name = name;
        this.profile = profile;
        this.userType = userType;
    }

    public static UserJpaEntity of(String email, String name, UserProfile profile, UserType userType) {
        return new UserJpaEntity(email, name, profile, userType);
    }
}

package co.kr.muldum.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "roles")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoleJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String role;

    @Column
    private String description;

    private RoleJpaEntity(String role, String description) {
        this.role = role;
        this.description = description;
    }

    public static RoleJpaEntity of(String role, String description) {
        return new RoleJpaEntity(role, description);
    }
}

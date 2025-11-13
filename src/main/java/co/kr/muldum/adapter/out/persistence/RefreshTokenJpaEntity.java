package co.kr.muldum.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "refresh_tokens")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshTokenJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(name = "refresh_token")
    private String refreshToken;

    private RefreshTokenJpaEntity(String email, String refreshToken) {
        this.email = email;
        this.refreshToken = refreshToken;
    }

    public static RefreshTokenJpaEntity of(String email, String refreshToken) {
        return new RefreshTokenJpaEntity(email, refreshToken);
    }

    public void updateToken(String newRefreshToken) {
        this.refreshToken = newRefreshToken;
    }
}
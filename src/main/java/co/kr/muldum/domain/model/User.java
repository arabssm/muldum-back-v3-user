package co.kr.muldum.domain.model;

import lombok.Getter;

import java.util.UUID;

@Getter
public class User {
    private final UUID id;
    private final String email;
    private final String name;
    private final UserProfile profile;
    private final UserType userType;

    protected User(UUID id,
                   String email,
                   String name,
                   UserProfile profile,
                   UserType userType) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.profile = profile;
        this.userType = userType;
    }
}

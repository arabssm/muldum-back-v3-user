package co.kr.muldum.domain.model;

public enum UserType {
    STUDENT("STUDENT"),
    TEACHER("TEACHER"),
    MENTOR("MENTOR");

    private final String value;

    UserType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static UserType fromValue(String value) {
        for (UserType type : UserType.values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown user type value: " + value);
    }
}

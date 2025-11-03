package co.kr.muldum.domain.model;

public enum Role {
    MENTO("mento");

    private final String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

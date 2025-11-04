package co.kr.muldum.domain.model;

public enum LogStatus {
    SUCCESS("success"),
    FAILURE("failure"),
    PENDING("pending");

    private final String value;

    LogStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
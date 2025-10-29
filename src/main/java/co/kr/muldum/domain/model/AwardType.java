package co.kr.muldum.domain.model;

public enum AwardType {
    GRAND_PRIZE("대상"),
    BEST_PRIZE("최우수상"),
    EXCELLENCE_PRIZE("우수상");

    private final String displayName;

    AwardType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
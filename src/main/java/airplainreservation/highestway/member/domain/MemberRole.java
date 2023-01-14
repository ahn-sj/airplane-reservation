package airplainreservation.highestway.member.domain;

public enum MemberRole {
    ROLE_USER("사용자"), ROLE_ADMIN("관리자");

    private String desc;

    MemberRole(String desc) {
        this.desc = desc;
    }
}
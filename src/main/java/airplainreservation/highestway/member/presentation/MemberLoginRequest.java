package airplainreservation.highestway.member.presentation;

import airplainreservation.highestway.member.domain.MemberRole;
import lombok.Getter;

@Getter
public class MemberLoginRequest {
    private String username;
    private String password;
    private String email;
    private MemberRole role = MemberRole.ROLE_USER;

    public MemberLoginRequest() {
    }

    public MemberLoginRequest(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}

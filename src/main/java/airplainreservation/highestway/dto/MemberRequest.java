package airplainreservation.highestway.dto;

import airplainreservation.highestway.member.domain.Member;
import airplainreservation.highestway.member.domain.MemberRole;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MemberRequest {

    @Getter
    public static class MemberRegisterRequest {
        private String username;
        private String password;
        private String email;
        private MemberRole role = MemberRole.ROLE_USER;

        public MemberRegisterRequest(String username, String password, String email) {
            this.username = username;
            this.password = password;
            this.email = email;
        }
    }

    @Getter
    public static class MemberLoginRequest {
        private String username;
        private String password;

        public MemberLoginRequest(String username, String password) {
            this.username = username;
            this.password = password;
        }
    }

}
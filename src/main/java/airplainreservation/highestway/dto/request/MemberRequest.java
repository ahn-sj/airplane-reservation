package airplainreservation.highestway.dto.request;

import airplainreservation.highestway.member.domain.Member;
import airplainreservation.highestway.member.domain.MemberRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

public class MemberRequest {

    @Getter
    @NoArgsConstructor
    public static class MemberRegisterRequest {
        @NotEmpty(message = "반드시 값을 입력해야 합니다.")
        private String username;
        @NotEmpty
        private String password;
        @NotEmpty
        private String email;
        private MemberRole role = MemberRole.ROLE_USER;

        public MemberRegisterRequest(String username, String password, String email) {
            this.username = username;
            this.password = password;
            this.email = email;
        }

        public void updateEncodedPassword(String encodedPassword) {
            this.password = encodedPassword;
        }

        public Member toEntity() {
            return Member.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .role(role)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor
    public static class MemberLoginRequest {
        private String username;
        private String password;

        public MemberLoginRequest(String username, String password) {
            this.username = username;
            this.password = password;
        }
    }
}
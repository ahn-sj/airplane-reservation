package airplainreservation.highestway.member.dto.response;

import airplainreservation.highestway.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberResponse {

    @Getter
    @NoArgsConstructor
    public static class MemberFindResponse {
        private Long id;
        private String username;
        private String email;
        private boolean enabled;
        private String role;

        @Builder
        public MemberFindResponse(Long id, String username, String email, boolean enabled, String role) {
            this.id = id;
            this.username = username;
            this.email = email;
            this.enabled = enabled;
            this.role = role;
        }

        public static MemberFindResponse of(Member member) {
            return new MemberFindResponse(member.getId(), member.getUsername(), member.getEmail(),
                    member.isEnabled(), member.getRoleName());
        }
    }
}

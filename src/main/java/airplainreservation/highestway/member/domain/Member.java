package airplainreservation.highestway.member.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
public class Member {

    @Id @GeneratedValue
    private Long id;

    private String username;
    private String password;
    private String email;

    private boolean enabled;

    @Enumerated(EnumType.STRING)
    private MemberRole role;

    @Builder
    public Member(String username, String password, String email, boolean enabled, MemberRole role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.enabled = enabled;
        this.role = role;
    }
}

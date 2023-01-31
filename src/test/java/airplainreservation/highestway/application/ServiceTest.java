package airplainreservation.highestway.application;

import airplainreservation.highestway.member.application.MemberService;
import airplainreservation.highestway.member.domain.Member;
import airplainreservation.highestway.member.domain.MemberRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class ServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    PasswordEncoder passwordEncoder;

    protected Member createMember(String username, String email) {
        return Member.builder()
                .username(username)
                .password(passwordEncoder.encode(username))
                .email(email)
                .role(MemberRole.ROLE_USER)
                .build();
    }
}

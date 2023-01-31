package airplainreservation.highestway.application;

import airplainreservation.highestway.common.dto.TokenResponse;
import airplainreservation.highestway.common.exception.CustomCommonException;
import airplainreservation.highestway.common.exception.ErrorCode;
import airplainreservation.highestway.member.domain.Member;
import airplainreservation.highestway.member.domain.MemberRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;

import static airplainreservation.highestway.member.dto.request.MemberRequest.MemberLoginRequest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class MemberServiceTest extends ServiceTest {

    @Test
    @DisplayName("MemberService 의 registerMember 은 회원을 생성한다.")
    void registerMemberTest() throws Exception {
        String username = "jae";
        String email = "jae@jae.com";
        Member member = createMember(username, email);

        Long memberId = memberService.registerMember(member);

        assertThat(member.getId()).isEqualTo(memberId);
    }

    @Test
    @DisplayName("MemberService 의 registerMember 는 중복된 이메일을 가지면 예외를 던진다.")
    void checkUniqueEmailTest() throws Exception {
        String username1 = "jae1";
        String username2 = "jae2";
        String email = "jae@jae.com";

        Member member1 = createMember(username1, email);
        Member member2 = createMember(username2, email);

        memberService.registerMember(member1);

        CustomCommonException exception = assertThrows(CustomCommonException.class, () -> {
            memberService.registerMember(member2);
        });
        assertEquals(exception.getMessage(), ErrorCode.DUPLICATE_EMAIL.getMessage());
        assertEquals(exception.getStatus(), ErrorCode.DUPLICATE_EMAIL.getStatus());
        assertEquals(exception.getHttpStatus(), ErrorCode.DUPLICATE_EMAIL.getHttpStatus());
    }

    @Test
    @DisplayName("MemberService 의 registerMember 는 중복된 닉네임 을 가지면 예외를 던진다.")
    void checkUniqueUsernameTest() throws Exception {
        String username = "jae";
        String email1 = "jae1@jae.com";
        String email2 = "jae2@jae.com";

        Member member1 = createMember(username, email1);
        Member member2 = createMember(username, email2);

        memberService.registerMember(member1);

        CustomCommonException exception = assertThrows(CustomCommonException.class, () -> {
            memberService.registerMember(member2);
        });
        assertEquals(exception.getMessage(), ErrorCode.DUPLICATE_USERNAME.getMessage());
    }

    @Test
    @DisplayName("MemberService 의 login 은 회원이 로그인한다.")
    void loginTest() throws Exception {
        // given
        String username = "jae";
        String email = "jae@jae.com";

        Member member = createMember(username, email);
        memberService.registerMember(member);

        // when
        MemberLoginRequest request = new MemberLoginRequest(username, username);
        MockHttpServletResponse httpServletResponse = new MockHttpServletResponse();

        TokenResponse tokenResponse = memberService.login(request, httpServletResponse);

        // then
        assertThat(tokenResponse.getAccessToken()).isNotEmpty();
    }

    @Test
    @DisplayName("MemberService 의 login 은 회원이 존재하지 않으면 예외를 던진다.")
    void findMemberByUsernameTest() throws Exception {
        // given
        String username = "jae";
        MemberLoginRequest request = new MemberLoginRequest(username, username);
        MockHttpServletResponse httpServletResponse = new MockHttpServletResponse();

        // when & then
        assertThatThrownBy(() -> {
            memberService.login(request, httpServletResponse);
        }).isInstanceOf(CustomCommonException.class);
    }

    @Test
    @DisplayName("MemberService 의 login 은 입력한 비밀번호와 저장된 비밀번호가 일치하지 않으면 예외를 던진다.")
    void checkPasswordTest() throws Exception {
        // given
        String username = "jae";
        String password = "pw_jae";

        Member member = Member.builder()
                .username(username)
                .password(passwordEncoder.encode(username))
                .email(username)
                .role(MemberRole.ROLE_USER)
                .build();
        memberService.registerMember(member);

        MemberLoginRequest request = new MemberLoginRequest(username, password);
        MockHttpServletResponse httpServletResponse = new MockHttpServletResponse();

        // when & then
        assertThatThrownBy(() -> memberService.login(request, httpServletResponse))
                .isInstanceOf(CustomCommonException.class);
    }

}

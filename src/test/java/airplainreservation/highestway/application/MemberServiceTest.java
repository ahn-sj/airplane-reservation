package airplainreservation.highestway.application;

import airplainreservation.highestway.member.application.MemberService;
import airplainreservation.highestway.member.domain.Member;
import airplainreservation.highestway.member.domain.MemberRole;
import airplainreservation.highestway.member.dto.response.MemberResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static airplainreservation.highestway.member.dto.response.MemberResponse.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    @DisplayName("saveReservationTest")
    void saveReservationTest() throws Exception {
        Member member = Member.builder()
                .username("jae")
                .password("jae")
                .email("jae@jae.kr")
                .role(MemberRole.ROLE_USER)
                .build();

        Long memberId = memberService.registerMember(member);
        MemberFindResponse response = memberService.find(memberId);

        assertThat(response.getUsername()).isEqualTo(member.getUsername());
    }
}

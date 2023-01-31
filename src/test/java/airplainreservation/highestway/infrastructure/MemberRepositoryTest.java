package airplainreservation.highestway.infrastructure;

import airplainreservation.highestway.member.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberRepositoryTest extends RepositoryTest {

    @Test
    @DisplayName("MemberRepository 의 findByUsername 은 이름을 기준으로 찾는다.")
    void findByUsernameTest() throws Exception {
        // given
        String username = "jae";
        Member member = createMember(username);
        memberRepository.save(member);

        // when
        Optional<Member> findMember = memberRepository.findByUsername(username);

        // then
        assertThat(findMember.isPresent()).isTrue();
    }

    @Test
    @DisplayName("MemberRepository 의 existsByEmail 는 중복된 이메일이 있는지 검사한다.")
    void existsByEmailTest() throws Exception {
        // given
        String username = "jae";
        Member member = createMember(username);
        memberRepository.save(member);

        // when
        Boolean isExist = memberRepository.existsByEmail(username);

        // then
        assertThat(isExist).isTrue();
    }

    @Test
    @DisplayName("MemberRepository 의 existsByUsername 는 중복된 닉네임이 있는지 검사한다.")
    void existsByUsernameTest() throws Exception {
        String username = "jae";
        Member member = createMember(username);
        memberRepository.save(member);

        // when
        Boolean isExist = memberRepository.existsByUsername(username);

        // then
        assertThat(isExist).isTrue();
    }
}

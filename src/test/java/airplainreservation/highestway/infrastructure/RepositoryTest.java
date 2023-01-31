package airplainreservation.highestway.infrastructure;

import airplainreservation.highestway.airplane.domain.Seat;
import airplainreservation.highestway.airplane.infrastructure.SeatRepository;
import airplainreservation.highestway.member.domain.Member;
import airplainreservation.highestway.member.domain.MemberRole;
import airplainreservation.highestway.member.infrastructure.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
abstract public class RepositoryTest {
    @Autowired
    SeatRepository seatRepository;

    @Autowired
    MemberRepository memberRepository;

    protected Seat createSeat(String seatNumber) {
        return Seat.builder().seatNumber(seatNumber).build();
    }

    protected Member createMember(String username) {
        return Member.builder()
                .username(username)
                .password(username)
                .email(username)
                .role(MemberRole.ROLE_USER)
                .build();
    }

}

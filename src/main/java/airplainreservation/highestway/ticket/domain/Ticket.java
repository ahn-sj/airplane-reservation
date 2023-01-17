package airplainreservation.highestway.ticket.domain;

import airplainreservation.highestway.airplane.domain.Seat;
import airplainreservation.highestway.common.domain.BaseTimeEntity;
import airplainreservation.highestway.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ticket extends BaseTimeEntity {

    @Id @GeneratedValue
    private Long id;

    private String passengerName;
    private String departure;
    private String arrival;

    private LocalDateTime boardingTime; // 탑승 시간

//    @ManyToOne(fetch = FetchType.LAZY)
//    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    private Seat seat;

}

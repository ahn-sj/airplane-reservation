package airplainreservation.highestway.airplane.domain;

import airplainreservation.highestway.common.domain.BaseTimeEntity;
import airplainreservation.highestway.ticket.domain.Ticket;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "seatRow", "seatCol"})
public class Seat extends BaseTimeEntity {

    @Id @GeneratedValue
    private Long id;

    private int seatRow; // 가로열
    private int seatCol; // 세로열

    @ManyToOne(fetch = FetchType.LAZY)
    private Airplane airplane;

    public Seat(int seatRow, int seatCol) {
        this.seatRow = seatRow;
        this.seatCol = seatCol;
    }

    public void addSeat(Airplane airplane) {
        this.airplane = airplane;
        airplane.getSeats().add(this);
    }
}

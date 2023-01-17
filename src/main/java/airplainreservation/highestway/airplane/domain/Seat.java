package airplainreservation.highestway.airplane.domain;

import airplainreservation.highestway.common.domain.BaseTimeEntity;
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

    private String seatNumber;

    private Boolean reservationEnable;

    @ManyToOne(fetch = FetchType.LAZY)
    private Airplane airplane;

    public Seat(String seatNumber) {
        this.seatNumber = seatNumber;

        reservationEnable = true;
    }

    public boolean isUnableToReserve() {
        return !reservationEnable;
    }

    // TODO: boolean 값을 UPDATE를 하는 방법중에 다른 방법은 없는지
    public void updateReservationEnable() {
        this.reservationEnable = !reservationEnable;
    }

    public void addSeat(Airplane airplane) {
        this.airplane = airplane;
        airplane.getSeats().add(this);
    }
}

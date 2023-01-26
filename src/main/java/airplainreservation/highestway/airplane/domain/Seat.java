package airplainreservation.highestway.airplane.domain;

import airplainreservation.highestway.common.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Seat extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String seatNumber;
    private Boolean reservationEnable;

    @ManyToOne(fetch = FetchType.LAZY)
    private Airplane airplane;

    @Builder
    public Seat(String seatNumber) {
        this.seatNumber = seatNumber;
        this.reservationEnable = Boolean.TRUE;
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

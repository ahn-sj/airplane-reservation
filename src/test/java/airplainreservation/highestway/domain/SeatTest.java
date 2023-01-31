package airplainreservation.highestway.domain;

import airplainreservation.highestway.airplane.domain.Airplane;
import airplainreservation.highestway.airplane.domain.Seat;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class SeatTest {
    @Test
    @DisplayName("Seat 의 isUnableToReserve 는 예약 상태의 반대되는 값이 나온다.")
    void isUnableToReserveTest() throws Exception {
        Seat seat = Seat.builder().seatNumber("A01").build();

        assertThat(seat.isUnableToReserve()).isFalse();
    }

    @Test
    @DisplayName("Seat 의 updateReservationEnable 는 예약 상태를 변경한다.")
    void updateReservationEnableTest() throws Exception {
        Seat seat = Seat.builder().seatNumber("A01").build();
        seat.updateReservationEnable();

        assertThat(seat.getReservationEnable()).isFalse();
    }

    @Test
    @DisplayName("Seat 의 addSeat 는 양방향 관계인 airplane 에 좌석을 추가한다.")
    void addSeatTest() throws Exception {
        // given
        Airplane airplane = Airplane.builder()
                .registrationNumber("A101")
                .departure("서울")
                .arrival("제주도")
                .build();
        Seat seat = Seat.builder().seatNumber("A01").build();

        // when
        seat.addSeat(airplane);

        // then
        assertThat(seat.getAirplane().getSeats().size()).isEqualTo(1);
    }
}

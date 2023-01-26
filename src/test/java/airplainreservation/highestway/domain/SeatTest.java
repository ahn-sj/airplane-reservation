package airplainreservation.highestway.domain;

import airplainreservation.highestway.airplane.domain.Seat;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SeatTest {
    @Test
    @DisplayName("Seat 의 isUnaBleToReserve 는 예약 상태의 반대되는 값이 나온다.")
    void isUnaBleToReserveTest() throws Exception {
        Seat seat = Seat.builder().seatNumber("A01").build();

        Assertions.assertThat(seat.isUnableToReserve()).isFalse();
    }

    @Test
    @DisplayName("Seat 의 updateReservationEnable 는 예약 상태를 변경한다.")
    void updateReservationEnableTest() throws Exception {
        Seat seat = Seat.builder().seatNumber("A01").build();
        seat.updateReservationEnable();

        Assertions.assertThat(seat.getReservationEnable()).isFalse();
    }
}

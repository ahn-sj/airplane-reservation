package airplainreservation.highestway.infrastructure;

import airplainreservation.highestway.airplane.domain.Seat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


public class SeatRepositoryTest extends RepositoryTest {

    @Test
    @DisplayName("seatRepository 의 findBySeatNumber 는 SeatNumber 를 기준으로 찾는다.")
    void findBySeatNumberTest() throws Exception {
        // given
        String seatNumber = "T01";
        Seat seat = createSeat(seatNumber);
        seatRepository.save(seat);

        // when
        Optional<Seat> findSeat = seatRepository.findBySeatNumber(seatNumber);

        // then
        assertThat(findSeat.isPresent()).isTrue();
    }
}

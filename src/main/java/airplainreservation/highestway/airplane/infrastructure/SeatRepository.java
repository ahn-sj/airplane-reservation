package airplainreservation.highestway.airplane.infrastructure;

import airplainreservation.highestway.airplane.domain.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
}

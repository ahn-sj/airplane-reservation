package airplainreservation.highestway.airplane.infrastructure;

import airplainreservation.highestway.airplane.domain.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirplaneRepository extends JpaRepository<Airplane, Long> {
}

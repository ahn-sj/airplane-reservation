package airplainreservation.highestway.airplane.application;

import airplainreservation.highestway.airplane.domain.Seat;
import airplainreservation.highestway.airplane.infrastructure.AirplaneRepository;
import airplainreservation.highestway.airplane.infrastructure.SeatRepository;
import airplainreservation.highestway.airplane.converter.RowColTowSeatsConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static airplainreservation.highestway.airplane.dto.request.AirplaneRequest.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class AirplaneService {

    private final AirplaneRepository airplaneRepository;
    private final SeatRepository seatRepository;

    @Transactional
    public void saveAirplaneWithSeatList(AirplaneRegisterRequest airplaneRegisterRequest) {
        List<Seat> seats = RowColTowSeatsConverter.convertFromRowColToSeats(airplaneRegisterRequest.getSeatRow(), airplaneRegisterRequest.getSeatCol());
        List<Seat> saveSeats = seatRepository.saveAll(seats);

        for (Seat seat : saveSeats) {
            System.out.println("seat = " + seat);
        }
    }
}

package airplainreservation.highestway.airplane.application;

import airplainreservation.highestway.airplane.domain.Airplane;
import airplainreservation.highestway.airplane.domain.Seat;
import airplainreservation.highestway.airplane.infrastructure.AirplaneRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static airplainreservation.highestway.airplane.dto.request.AirplaneRequest.AirplaneRegisterRequest;

@Service
@RequiredArgsConstructor
@Slf4j
public class AirplaneService {

    private final int SEAT_NUMBER_START = 1;

    private final AirplaneRepository airplaneRepository;

    @Transactional
    public Long saveAirplaneWithSeatList(AirplaneRegisterRequest airplaneRegisterRequest) {
        int seatRow = airplaneRegisterRequest.getSeatRow();
        int seatCol = airplaneRegisterRequest.getSeatCol();
        String registrationNumber = airplaneRegisterRequest.getRegistrationNumber();
        String departure = airplaneRegisterRequest.getDeparture();
        String arrival = airplaneRegisterRequest.getArrival();

        Airplane airplane = airplaneRepository.save(new Airplane(registrationNumber, departure, arrival));

        // TODO: Converter 를 활용한 리팩토링 대상
        createFromRowColToSeat(airplane, seatRow, seatCol);

        return airplane.getId();
    }

    private void createFromRowColToSeat(Airplane airplane, int seatRow, int seatCol) {
        for (int i = SEAT_NUMBER_START; i <= seatRow; i++) {
            for (int j = SEAT_NUMBER_START; j <= seatCol; j++) {
                Seat seat = new Seat(i, j);
                seat.addSeat(airplane);
            }
        }
    }
}

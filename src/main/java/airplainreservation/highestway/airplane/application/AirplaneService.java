package airplainreservation.highestway.airplane.application;

import airplainreservation.highestway.airplane.domain.Airplane;
import airplainreservation.highestway.airplane.domain.Seat;
import airplainreservation.highestway.airplane.infrastructure.AirplaneRepository;
import airplainreservation.highestway.common.exception.CustomCommonException;
import airplainreservation.highestway.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static airplainreservation.highestway.airplane.dto.request.AirplaneRequest.AirplaneRegisterRequest;

@Service
@RequiredArgsConstructor
@Slf4j
public class AirplaneService {

    private final int SEAT_ROW_START = 0;
    private final int SEAT_COLUMN_START = 1;
    private final int SEAT_ROW_SIZE = 0;

    private final AirplaneRepository airplaneRepository;

    @Transactional
    public Long saveAirplaneWithSeatList(AirplaneRegisterRequest airplaneRegisterRequest) {
        int seatRow = airplaneRegisterRequest.getSeatRow();
        int seatCol = airplaneRegisterRequest.getSeatCol();

        Airplane airplane = airplaneRepository.save(airplaneRegisterRequest.toEntity());

        // TODO: Converter 를 활용한 리팩토링 대상
        createFromRowColToSeat(airplane, seatRow, seatCol);

        return airplane.getId();
    }

    private void createFromRowColToSeat(Airplane airplane, int seatRow, int seatCol) {
        validateSeatSize(seatRow, seatCol);

        for (int i = SEAT_ROW_START; i < seatRow; i++) {
            for (int j = SEAT_COLUMN_START; j <= seatCol; j++) {
                String seatNumber = (char)('A' + i) + "" + SEAT_ROW_SIZE + "" + j;

                Seat seat = new Seat(seatNumber);
                seat.addSeat(airplane);
            }
        }
    }

    private static void validateSeatSize(int seatRow, int seatCol) {
        if(isValidSeatSize(seatRow, seatCol)) {
            throw new CustomCommonException(ErrorCode.SEAT_OUT_OF_BOUNDS);
        }
    }

    private static boolean isValidSeatSize(int seatRow, int seatCol) {
        return seatRow > 26 || seatCol < 10 || seatRow < 1 || seatCol < 1;
    }
}

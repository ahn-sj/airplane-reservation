package airplainreservation.highestway.airplane.converter;

import airplainreservation.highestway.airplane.domain.Seat;

import java.util.ArrayList;
import java.util.List;

public class RowColTowSeatsConverter {

    public static List<Seat> convertFromRowColToSeats(int seatRow, int seatCol) {
        List<Seat> seats = new ArrayList<>();

        for (int i = 0; i < seatRow; i++) {
            for (int j = 0; j < seatCol; j++) {
                seats.add(new Seat(seatRow, seatCol));
            }
        }

        return seats;
    }
}

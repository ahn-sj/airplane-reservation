package airplainreservation.highestway.airplane.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

public class AirplaneRequest {

    @NoArgsConstructor
    @Getter
    public static class AirplaneRegisterRequest {
        private String registrationNumber;
        private int seatCol;
        private int seatRow;

        public AirplaneRegisterRequest(String registrationNumber, int seatCol, int seatRow) {
            this.registrationNumber = registrationNumber;
            this.seatCol = seatCol;
            this.seatRow = seatRow;
        }
    }
}

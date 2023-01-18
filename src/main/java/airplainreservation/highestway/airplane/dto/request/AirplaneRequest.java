package airplainreservation.highestway.airplane.dto.request;

import airplainreservation.highestway.airplane.domain.Airplane;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class AirplaneRequest {

    @NoArgsConstructor
    @Getter
    public static class AirplaneRegisterRequest {
        private String registrationNumber;

        private int seatCol;
        private int seatRow;

        private String departure;
        private String arrival;

        public AirplaneRegisterRequest(String registrationNumber, int seatCol, int seatRow, String departure, String arrival) {
            this.registrationNumber = registrationNumber;
            this.seatCol = seatCol;
            this.seatRow = seatRow;
            this.departure = departure;
            this.arrival = arrival;
        }

        public Airplane toEntity() {
            return Airplane.builder()
                    .registrationNumber(registrationNumber)
                    .departure(departure)
                    .arrival(arrival)
                    .build();
        }
    }
}

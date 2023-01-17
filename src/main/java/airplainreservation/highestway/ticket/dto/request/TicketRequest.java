package airplainreservation.highestway.ticket.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class TicketRequest {

    @Getter
    @NoArgsConstructor
    public static class TicketCreateRequest {
        private String seatNumber;
        private LocalDateTime reservationTime;

        @Builder
        public TicketCreateRequest(String seatNumber, LocalDateTime reservationTime) {
            this.seatNumber = seatNumber;
            this.reservationTime = reservationTime;
        }
    }
}

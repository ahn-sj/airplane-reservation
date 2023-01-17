package airplainreservation.highestway.ticket.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

public class TicketRequest {

    @Getter
    @NoArgsConstructor
    public static class TicketCreateRequest {
        private String seatNumber;

    }
}

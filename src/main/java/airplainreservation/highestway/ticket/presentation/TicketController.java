package airplainreservation.highestway.ticket.presentation;

import airplainreservation.highestway.common.security.UserPrincipal;
import airplainreservation.highestway.ticket.application.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static airplainreservation.highestway.ticket.dto.request.TicketRequest.TicketCreateRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ticket")
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/{airplaneId}/reservations")
    public ResponseEntity<Void> create(@PathVariable Long airplaneId,
                                         @RequestBody TicketCreateRequest ticketCreateRequest,
                                         @AuthenticationPrincipal UserPrincipal userPrincipal) {
        System.out.println("TicketController.create");
        Long ticketId = ticketService.saveReservation(
                airplaneId,
                userPrincipal.getMember().getId(),
                ticketCreateRequest
        );

        return ResponseEntity.created(URI.create("/api/ticket/" + airplaneId + "/reservations/" + ticketId)).build();
    }

}

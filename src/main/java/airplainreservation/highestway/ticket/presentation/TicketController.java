package airplainreservation.highestway.ticket.presentation;

import airplainreservation.highestway.common.security.UserPrincipal;
import airplainreservation.highestway.ticket.application.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static airplainreservation.highestway.ticket.dto.request.TicketRequest.TicketCreateRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ticket")
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/{airplaneId}/reservations")
    public void create(@PathVariable Long airplaneId,
                       @RequestBody TicketCreateRequest ticketCreateRequest,
                       @AuthenticationPrincipal UserPrincipal userPrincipal) {
//        ticketService.saveReservation(userPrincipal.getMember())
    }

}

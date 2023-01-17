package airplainreservation.highestway.ticket.infrastructure;

import airplainreservation.highestway.ticket.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

}

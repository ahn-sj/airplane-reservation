package airplainreservation.highestway.ticket.application;

import airplainreservation.highestway.airplane.domain.Airplane;
import airplainreservation.highestway.airplane.domain.Seat;
import airplainreservation.highestway.airplane.infrastructure.AirplaneRepository;
import airplainreservation.highestway.airplane.infrastructure.SeatRepository;
import airplainreservation.highestway.common.exception.CustomCommonException;
import airplainreservation.highestway.common.exception.ErrorCode;
import airplainreservation.highestway.member.domain.Member;
import airplainreservation.highestway.member.infrastructure.MemberRepository;
import airplainreservation.highestway.ticket.domain.Ticket;
import airplainreservation.highestway.ticket.infrastructure.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static airplainreservation.highestway.ticket.dto.request.TicketRequest.TicketCreateRequest;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final MemberRepository memberRepository;
    private final AirplaneRepository airplaneRepository;
    private final SeatRepository seatRepository;

    @Transactional
    public Long saveReservation(Long airplaneId, Long memberId, TicketCreateRequest ticketCreateRequest) {
        Member member = findMember(memberId);
        Airplane airplane = findAirplane(airplaneId);
        Seat seat = findSeat(ticketCreateRequest.getSeatNumber());

        seat.updateReservationEnable();

        Ticket ticket = Ticket.builder()
                .passengerName(member.getUsername())
                .departure(airplane.getDeparture())
                .arrival(airplane.getArrival())
                .boardingTime(ticketCreateRequest.getReservationTime())
                .seatNumber(seat.getSeatNumber())
                .build();

        Ticket saveTicket = ticketRepository.save(ticket);

        return saveTicket.getId();
    }

    private static void isEnabledSeat(Seat seat) {
        if(seat.isUnableToReserve()) {
            throw new CustomCommonException(ErrorCode.RESERVED_AIRPLANE_SEAT);
        }
    }

    private Seat findSeat(String seatNumber) {
        Seat seat = seatRepository.findBySeatNumber(seatNumber).orElseThrow(
                () -> new CustomCommonException(ErrorCode.NOT_EXISTS_SEAT)
        );
        isEnabledSeat(seat);

        return seat;
    }

    private Airplane findAirplane(Long airplaneId) {
        return airplaneRepository.findById(airplaneId).orElseThrow(
                () -> new CustomCommonException(ErrorCode.NOT_EXISTS_AIRPLANE)
        );
    }

    private Member findMember(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(
                () -> new CustomCommonException(ErrorCode.NOT_EXISTS_MEMBER)
        );
    }
}

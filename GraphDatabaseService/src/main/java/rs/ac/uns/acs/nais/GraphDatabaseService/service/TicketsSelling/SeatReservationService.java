package rs.ac.uns.acs.nais.GraphDatabaseService.service.TicketsSelling;

import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.Fan;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.Order;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.SeatReservation;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.TicketsSelling.FanRepository;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.TicketsSelling.SeatReservationRepository;

import java.util.List;


@Service
public class SeatReservationService {

    private SeatReservationRepository seatReservationRepository;

    public SeatReservationService(SeatReservationRepository seatReservationRepository) {
        this.seatReservationRepository = seatReservationRepository;
    }

    public SeatReservation createSeatReservation(SeatReservation seatReservation) {
        return seatReservationRepository.createSeatReservation(seatReservation.getFootballMatchID(), seatReservation.getSeatNumber());
    }

    public SeatReservation findSeatReservation(String footballMatchID, String seatNumber) {
        return seatReservationRepository.findSeatReservation(footballMatchID, seatNumber);
    }

    public SeatReservation updateSeatReservation(String footballMatchID, String seatNumber, SeatReservation seatReservation) {
        return seatReservationRepository.updateSeatReservation(footballMatchID, seatNumber, seatReservation.getFootballMatchID(), seatReservation.getSeatNumber());
    }

    public boolean deleteSeatReservation(String footballMatchID, String seatNumber) {
        return seatReservationRepository.deleteSeatReservation(footballMatchID, seatNumber);
    }

}

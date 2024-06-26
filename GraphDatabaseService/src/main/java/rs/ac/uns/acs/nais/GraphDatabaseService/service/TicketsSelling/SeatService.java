package rs.ac.uns.acs.nais.GraphDatabaseService.service.TicketsSelling;

import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.TicketsSelling.RecommendedSeasonTicketSeatsDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.TicketsSelling.RecommendedStandardTicketSeatsDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.TicketsSelling.SeasonTicketStartTimeDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.Seat;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.SeatReservation;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.TicketsSelling.SeatRepository;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.TicketsSelling.SeatReservationRepository;

import java.util.List;


@Service
public class SeatService {

    private SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public Seat createSeat(Seat seat) {
        return seatRepository.createSeat(seat.getSeatNumber(), seat.getSector(), seat.getTribune());
    }

    public Seat findSeat(String seatNumber) {
        return seatRepository.findSeat(seatNumber);
    }

    public Seat updateSeat(String seatNumber, Seat seat) {
        return seatRepository.updateSeat(seatNumber, seat.getSeatNumber(), seat.getSector(), seat.getTribune());
    }

    public boolean deleteSeat(String seatNumber) {
        return seatRepository.deleteSeat(seatNumber);
    }

    public List<RecommendedStandardTicketSeatsDTO> recommendStandardTicketSeat(String fanUsername) {
        return seatRepository.recommendStandardTicketSeat(fanUsername);
    }

    public List<RecommendedSeasonTicketSeatsDTO> recommendSeasonTicketSeat(String fanUsername, SeasonTicketStartTimeDTO seasonTicketStartTime) {
        return seatRepository.recommendSeasonTicketSeat(fanUsername, seasonTicketStartTime.getSeasonTicketStartTime());
    }
}

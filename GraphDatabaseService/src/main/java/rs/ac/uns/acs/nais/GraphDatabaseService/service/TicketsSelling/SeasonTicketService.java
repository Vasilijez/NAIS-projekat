package rs.ac.uns.acs.nais.GraphDatabaseService.service.TicketsSelling;

import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.SeasonTicket;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.TicketsSelling.SeasonTicketRepository;


@Service
public class SeasonTicketService {

    private SeasonTicketRepository seasonTicketRepository;

    public SeasonTicketService(SeasonTicketRepository seasonTicketRepository) {
        this.seasonTicketRepository = seasonTicketRepository;
    }

    public SeasonTicket createSeasonTicket(SeasonTicket seasonTicket) {
        return seasonTicketRepository.createSeasonTicket(seasonTicket.getTicketID(), seasonTicket.getPrice(), seasonTicket.getStartTime(), seasonTicket.getEndTime(), seasonTicket.getOrderID());
    }

    public SeasonTicket findSeasonTicket(String ticketID) {
        return seasonTicketRepository.findSeasonTicket(ticketID);
    }

    public SeasonTicket updateSeasonTicket(String ticketID, SeasonTicket seasonTicket) {
        return seasonTicketRepository.updateSeasonTicket(ticketID, seasonTicket.getTicketID(), seasonTicket.getPrice(), seasonTicket.getStartTime(), seasonTicket.getEndTime(), seasonTicket.getOrderID());
    }

    public boolean deleteSeasonTicket(String ticketID) {
        return seasonTicketRepository.deleteSeasonTicket(ticketID);
    }

}

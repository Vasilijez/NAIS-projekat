package rs.ac.uns.acs.nais.GraphDatabaseService.service.TicketsSelling;

import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.Ticket;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.TicketsSelling.TicketRepository;


@Service
public class TicketService {

    private TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.createTicket(ticket.getTicketID(), ticket.getPrice(), ticket.getOrderID());
    }

    public Ticket findTicket(String ticketID) {
        return ticketRepository.findTicket(ticketID);
    }

    public Ticket updateTicket(String ticketID, Ticket ticket) {
        return ticketRepository.updateTicket(ticketID, ticket.getTicketID(), ticket.getPrice(), ticket.getOrderID());
    }

    public boolean deleteTicket(String ticketID) {
        return ticketRepository.deleteTicket(ticketID);
    }

}

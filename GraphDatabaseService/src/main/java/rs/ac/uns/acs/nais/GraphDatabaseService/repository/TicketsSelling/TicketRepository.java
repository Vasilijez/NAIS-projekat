package rs.ac.uns.acs.nais.GraphDatabaseService.repository.TicketsSelling;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.Seat;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.Ticket;

@Repository
public interface TicketRepository extends Neo4jRepository<Ticket, Long> {

    @Query("CREATE (t:Ticket {ticketID: $ticketID, price: $price, orderID: $orderID}) " +
            "RETURN t ")
    Ticket createTicket(String ticketID, Double price, String orderID);

    @Query("MATCH (t:Ticket {ticketID: $ticketID}) " +
            "RETURN t ")
    Ticket findTicket(String ticketID);

    @Query("MATCH (t:Ticket {ticketID: $ticketID}) " +
            "SET t.ticketID = $updatingTicketID, t.price = $price, t.orderID = $orderID " +
            "RETURN t ")
    Ticket updateTicket(String ticketID, String updatingTicketID, Double price, String orderID);

    @Query("MATCH (t:Ticket {ticketID: $ticketID}) " +
            "DETACH DELETE t " +
            "RETURN count(t) > 0 ")
    boolean deleteTicket(String ticketID);

}

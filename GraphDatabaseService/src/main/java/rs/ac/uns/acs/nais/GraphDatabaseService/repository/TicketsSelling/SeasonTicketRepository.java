package rs.ac.uns.acs.nais.GraphDatabaseService.repository.TicketsSelling;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.SeasonTicket;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.Ticket;

import java.time.LocalDate;

@Repository
public interface SeasonTicketRepository extends Neo4jRepository<SeasonTicket, Long> {

    @Query("CREATE (t:Ticket:SeasonTicket {ticketID: $ticketID, price: $price, startTime: $startTime, endTime: $endTime, orderID: $orderID}) " +
            "RETURN t ")
    SeasonTicket createSeasonTicket(String ticketID, Double price, LocalDate startTime, LocalDate endTime, String orderID);

    @Query("MATCH (t:Ticket:SeasonTicket {ticketID: $ticketID}) " +
            "RETURN t ")
    SeasonTicket findSeasonTicket(String ticketID);

    @Query("MATCH (t:Ticket:SeasonTicket {ticketID: $ticketID}) " +
            "SET t.ticketID = $updatingTicketID, t.price = $price, t.startTime = $startTime, t.endTime = $endTime, t.orderID = $orderID " +
            "RETURN t ")
    SeasonTicket updateSeasonTicket(String ticketID, String updatingTicketID, Double price, LocalDate startTime, LocalDate endTime, String orderID);

    @Query("MATCH (t:Ticket:SeasonTicket {ticketID: $ticketID}) " +
            "DETACH DELETE t " +
            "RETURN count(t) > 0 ")
    boolean deleteSeasonTicket(String ticketID);

}

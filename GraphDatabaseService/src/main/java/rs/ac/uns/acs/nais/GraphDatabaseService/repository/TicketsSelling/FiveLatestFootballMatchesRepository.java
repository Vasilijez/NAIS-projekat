package rs.ac.uns.acs.nais.GraphDatabaseService.repository.TicketsSelling;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.TicketsSelling.FiveLatestFootballMatchesDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.TicketsSelling.PopularFootballMatchDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.FootballMatch;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.Seat;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface FiveLatestFootballMatchesRepository extends Neo4jRepository<FiveLatestFootballMatchesDTO, Long> {

    @Query("MATCH (fm:FootballMatch) " +
            "WITH fm " +
            "ORDER BY date(fm.startTime) DESC " +
            "LIMIT 5 " +
            "MATCH (fm)-[:HAS_RESERVATION]->(sr:SeatReservation)  " +
            "WITH fm, COUNT(sr) AS reservedTicketsNumber " +
            "OPTIONAL MATCH (fm)-[:HAS_RESERVATION]->(sr:SeatReservation), (sr)-[:VALID_FOR]->(t:Ticket), (t)<-[:CONSISTS_OF]-(o:Order)  " +
            "WHERE o.status = \"Cancelled\" " +
            "WITH fm, COUNT(t) AS cancelledTicketsNumber, reservedTicketsNumber " +
            "RETURN fm.footballMatchID, fm.opponentName, fm.result, reservedTicketsNumber, cancelledTicketsNumber, reservedTicketsNumber - cancelledTicketsNumber AS paidTicketsNumber " +
            "ORDER BY paidTicketsNumber DESC ")
    List<FiveLatestFootballMatchesDTO> getTicketsStatsFromFiveLatestFootballMatches();


}

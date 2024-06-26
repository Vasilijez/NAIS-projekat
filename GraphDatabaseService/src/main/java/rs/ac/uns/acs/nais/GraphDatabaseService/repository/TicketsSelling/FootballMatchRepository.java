package rs.ac.uns.acs.nais.GraphDatabaseService.repository.TicketsSelling;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.TicketsSelling.FiveLatestFootballMatchesDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.TicketsSelling.PopularFootballMatchDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.FootballMatch;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.Seat;

import java.util.Date;
import java.util.List;

@Repository
public interface FootballMatchRepository extends Neo4jRepository<FootballMatch, Long> {

    @Query("CREATE (fm: FootballMatch {footballMatchID: $footballMatchID, startTime: $startTime, opponentName: $opponentName, result: $result}) " +
            "RETURN fm ")
    FootballMatch createFootballMatch(String footballMatchID, Date startTime, String opponentName, String result);

    @Query("MATCH (fm: FootballMatch {footballMatchID: $footballMatchID}) " +
            "RETURN fm  ")
    FootballMatch findFootballMatch(String footballMatchID);

    @Query("MATCH (fm: FootballMatch {footballMatchID: $footballMatchID}) " +
            "SET fm.footballMatchID = $updatingFootballMatchID, fm.startTime = $startTime, fm.opponentName = $opponentName, fm.result = $result " +
            "RETURN fm ")
    FootballMatch updateFootballMatch(String footballMatchID, String updatingFootballMatchID, Date startTime, String opponentName, String result);

    @Query("MATCH (fm: FootballMatch {footballMatchID: $footballMatchID}) " +
            "DETACH DELETE fm " +
            "RETURN count(fm) > 0 ")
    boolean deleteFootballMatch(String footballMatchID);

//    @Query("MATCH (fm:FootballMatch) " +
//            "WITH fm " +
//            "ORDER BY date(fm.startTime) DESC " +
//            "LIMIT 5 " +
//            "MATCH (fm)-[:HAS_RESERVATION]->(sr:SeatReservation)  " +
//            "WITH fm, COUNT(sr) AS reservedTicketsNumber " +
//            "OPTIONAL MATCH (fm)-[:HAS_RESERVATION]->(sr:SeatReservation), (sr)-[:VALID_FOR]->(t:Ticket), (t)<-[:CONSISTS_OF]-(o:Order)  " +
//            "WHERE o.status = \"Cancelled\" " +
//            "WITH fm, COUNT(t) AS cancelledTicketsNumber, reservedTicketsNumber " +
//            "RETURN fm AS footballMatch, reservedTicketsNumber, cancelledTicketsNumber, reservedTicketsNumber - cancelledTicketsNumber AS paidTicketsNumber " +
//            "ORDER BY paidTicketsNumber DESC ")
//    List<FiveLatestFootballMatchesDTO> getTicketsStatsFromFiveLatestFootballMatches();

    @Query("MATCH (fm:FootballMatch)  " +
            "WHERE fm.startTime >= datetime() " +
            "MATCH (fm)-[:HAS_RESERVATION]->(sr:SeatReservation) " +
            "WITH fm, COUNT(sr) AS totalSeatReservationsNumber " +
            "MATCH (rFan:Fan) " +
            "WHERE rFan.username = $fanUsername " +
            "WITH rFan, fm, totalSeatReservationsNumber " +
            "MATCH (rFan)-[r:CREATES_ORDER]->(o:Order) " +
            "WHERE o.status <> \"Cancelled\" " +
            "WITH rFan,fm, o,r, totalSeatReservationsNumber " +
            "OPTIONAL MATCH (o)-[:CONSISTS_OF]->(t:Ticket), (t)<-[:VALID_FOR]-(sr:SeatReservation), (sr)<-[:HAS_RESERVATION]-(fm2:FootballMatch) " +
            "WHERE fm = fm2  " +
            "WITH fm, COLLECT(sr) as reservations, totalSeatReservationsNumber  " +
            "RETURN DISTINCT fm as footballMatch, totalSeatReservationsNumber, CASE WHEN size(reservations) = 0 THEN 'NO' ELSE 'YES' END AS hasTicket " +
            "ORDER BY hasTicket, totalSeatReservationsNumber DESC ")
    List<PopularFootballMatchDTO> recommendPopularFootballMatch(String fanUsername);

}

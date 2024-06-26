package rs.ac.uns.acs.nais.GraphDatabaseService.repository.TicketsSelling;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.TicketsSelling.RecommendedSeasonTicketSeatsDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.TicketsSelling.RecommendedStandardTicketSeatsDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.Seat;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface SeatRepository extends Neo4jRepository<Seat, Long> {

    @Query("CREATE (s:Seat {seatNumber: $seatNumber, sector: $sector, tribune: $tribune}) " +
            "RETURN s ")
    Seat createSeat(String seatNumber, Integer sector, String tribune);

    @Query("MATCH (s:Seat {seatNumber: $seatNumber}) " +
            "RETURN s  ")
    Seat findSeat(String seatNumber);

    @Query("MATCH (s:Seat {seatNumber: $seatNumber}) " +
            "SET s.seatNumber = $updatingSeatNumber, s.sector = $sector, s.tribune = $tribune " +
            "RETURN s ")
    Seat updateSeat(String seatNumber, String updatingSeatNumber, Integer sector, String tribune);

    @Query("MATCH (s:Seat {seatNumber: $seatNumber}) " +
            "DETACH DELETE s " +
            "RETURN count(s) > 0 ")
    boolean deleteSeat(String seatNumber);


    @Query("MATCH (rFan:Fan) " +
            "WHERE rFan.username = $fanUsername " +
            "WITH rFan " +
            "MATCH (rFan)-[:CREATES_ORDER]->(o:Order) " +
            "WHERE o.status <> \"Cancelled\" and o.status <> \"InCart\" " +
            "WITH rFan, o " +
            "MATCH (o)-[:CONSISTS_OF]->(t:Ticket) " +
            "WHERE NOT EXISTS((o)-[:CONSISTS_OF]->(t:Ticket:SeasonTicket)) " +
            "WITH rFan, COUNT(t) as rFanStandardTicketsNumber " +
            "MATCH (otherFans:Fan) " +
            "WHERE otherFans.username <> rFan.username and otherFans.favouritePlayer = rFan.favouritePlayer and otherFans.fanType = rFan.fanType " +
            "WITH otherFans, rFan, rFanStandardTicketsNumber " +
            "MATCH (otherFans)-[:CREATES_ORDER]->(o:Order) " +
            "WHERE o.status <> \"Cancelled\" and o.status <> \"InCart\" " +
            "WITH otherFans, o, rFan, rFanStandardTicketsNumber " +
            "MATCH (o)-[:CONSISTS_OF]->(t:Ticket) " +
            "WHERE NOT EXISTS((o)-[:CONSISTS_OF]->(t:Ticket:SeasonTicket)) " +
            "WITH otherFans, COUNT(t) as otherFansStandardTicketsNumber, rFan, rFanStandardTicketsNumber " +
            "WHERE rFanStandardTicketsNumber - 20 <= otherFansStandardTicketsNumber <= rFanStandardTicketsNumber + 20 AND rFan.dateOfBirth - duration({years: 5}) <= otherFans.dateOfBirth <= rFan.dateOfBirth + duration({years: 5}) " +
            "MATCH (otherFans)-[:CREATES_ORDER]->(o:Order) " +
            "WHERE o.status <> \"Cancelled\" and o.status <> \"InCart\" " +
            "MATCH (o)-[:CONSISTS_OF]->(t:Ticket) " +
            "WHERE NOT EXISTS((o)-[:CONSISTS_OF]->(t:Ticket:SeasonTicket)) " +
            "MATCH (t)<-[:VALID_FOR]-(sr:SeatReservation), (sr)<-[:BOOKED_FOR]-(s:Seat) " +
            "WITH s, COUNT(sr) as seatReservationCount " +
            "MATCH (fm:FootballMatch) " +
            "WHERE fm.footballMatchID = 'FM1' " +
            "OPTIONAL MATCH (fm)-[:HAS_RESERVATION]->(sr:SeatReservation), (sr)<-[:BOOKED_FOR]-(s) " +
            "RETURN DISTINCT s as seat, seatReservationCount, CASE WHEN sr IS NULL THEN 'NO' ELSE 'YES' END AS isReserved " +
            "ORDER BY isReserved, seatReservationCount DESC ")
    List<RecommendedStandardTicketSeatsDTO> recommendStandardTicketSeat(String fanUsername);


    @Query("MATCH (rFan:Fan)-[r:MEMBER_OF]->(:LoyaltyProgram) " +
            "WHERE rFan.username = $fanUsername " +
            "WITH rFan, r.pointsNumber as rFanPointsNumber " +
            "MATCH (otherFans:Fan)-[r:MEMBER_OF]->(:LoyaltyProgram) " +
            "WHERE otherFans.username <> rFan.username and rFanPointsNumber - 50 <= r.pointsNumber <=rFanPointsNumber + 50  " +
            "WITH rFan, otherFans " +
            "MATCH (otherFans)-[:CREATES_ORDER]->(o:Order), (o)-[:CONSISTS_OF]->(t:Ticket) " +
            "WHERE o.status <> \"Cancelled\" AND o.status <> \"InCart\" AND EXISTS((o)-[:CONSISTS_OF]->(t:Ticket:SeasonTicket)) " +
            "WITH otherFans " +
            "MATCH (otherFans)-[:CREATES_ORDER]->(o:Order) " +
            "WHERE o.status <> \"Cancelled\" and o.status <> \"InCart\" " +
            "MATCH (o)-[:CONSISTS_OF]->(t:Ticket), (t)<-[:VALID_FOR]-(sr:SeatReservation), (sr)<-[:BOOKED_FOR]-(s:Seat) " +
            "WITH s, COUNT(DISTINCT sr) AS seatReservationCountEver " +
            "MATCH (fm:FootballMatch) " +
            "WHERE localDatetime($seasonTicketStartTime) <= fm.startTime <= localDatetime($seasonTicketStartTime) + duration({years: 1}) " +
            "OPTIONAL MATCH (fm)-[:HAS_RESERVATION]->(sr:SeatReservation), (sr)<-[:BOOKED_FOR]-(s) " +
            "WITH s, COLLECT(sr) as reservations, seatReservationCountEver " +
            "RETURN DISTINCT s as seat, CASE WHEN size(reservations) = 0 THEN 'NO' ELSE 'YES' END AS isReserved, seatReservationCountEver " +
            "ORDER BY isReserved, seatReservationCountEver DESC ")
    List<RecommendedSeasonTicketSeatsDTO> recommendSeasonTicketSeat(String fanUsername, LocalDateTime seasonTicketStartTime);

}

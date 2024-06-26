package rs.ac.uns.acs.nais.GraphDatabaseService.repository.TicketsSelling;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.SeatReservation;
import java.time.LocalDate;

@Repository
public interface SeatReservationRepository extends Neo4jRepository<SeatReservation, Long> {

    @Query("CREATE (sr:SeatReservation {footballMatchID: $footballMatchID, seatNumber: $seatNumber}) " +
            "RETURN sr ")
    SeatReservation createSeatReservation(String footballMatchID, String seatNumber);

    @Query("MATCH (sr:SeatReservation {footballMatchID: $footballMatchID, seatNumber: $seatNumber}) " +
            "RETURN sr  ")
    SeatReservation findSeatReservation(String footballMatchID, String seatNumber);

    @Query("MATCH (sr:SeatReservation {footballMatchID: $footballMatchID, seatNumber: $seatNumber}) " +
            "SET sr.footballMatchID = $updatingFootballMatchID, sr.seatNumber = $updatingSeatNumber " +
            "RETURN sr ")
    SeatReservation updateSeatReservation(String footballMatchID, String seatNumber, String updatingFootballMatchID, String updatingSeatNumber);

    @Query("MATCH (sr:SeatReservation {footballMatchID: $footballMatchID, seatNumber: $seatNumber}) " +
            "DETACH DELETE sr " +
            "RETURN count(sr) > 0 ")
    boolean deleteSeatReservation(String footballMatchID, String seatNumber);

}

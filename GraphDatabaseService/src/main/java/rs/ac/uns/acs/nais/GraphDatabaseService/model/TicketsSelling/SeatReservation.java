package rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node("SeatReservation")
public class SeatReservation {

    @Id
    @GeneratedValue
    private Long id;

    private String footballMatchID;

    private String seatNumber;

    @Relationship(type = "VALID_FOR", direction = Relationship.Direction.OUTGOING)
    private List<Ticket> tickets;

    public SeatReservation() {
    }

    public SeatReservation(Long id, String footballMatchID, String seatNumber) {
        this.id = id;
        this.footballMatchID = footballMatchID;
        this.seatNumber = seatNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFootballMatchID() {
        return footballMatchID;
    }

    public void setFootballMatchID(String footballMatchID) {
        this.footballMatchID = footballMatchID;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    @Override
    public String toString() {
        return "SeatReservation {" +
                "id=" + id +
                ", footballMatchID='" + footballMatchID + '\'' +
                ", seatNumber='" + seatNumber + '\'' +
                '}';
    }
}

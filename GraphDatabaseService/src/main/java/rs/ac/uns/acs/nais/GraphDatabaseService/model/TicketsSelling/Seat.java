package rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node("Seat")
public class Seat {

    @Id
    @GeneratedValue
    private Long id;

    private String seatNumber;

    private Integer sector;

    private String tribune;

    @Relationship(type = "BOOKED_FOR", direction = Relationship.Direction.OUTGOING)
    private List<SeatReservation> seatReservations;

    public Seat() {
    }

    public Seat(Long id, String seatNumber, Integer sector, String tribune) {
        this.id = id;
        this.seatNumber = seatNumber;
        this.sector = sector;
        this.tribune = tribune;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Integer getSector() {
        return sector;
    }

    public void setSector(Integer sector) {
        this.sector = sector;
    }

    public String getTribune() {
        return tribune;
    }

    public void setTribune(String tribune) {
        this.tribune = tribune;
    }

    @Override
    public String toString() {
        return "Seat {" +
                "id=" + id +
                ", seatNumber='" + seatNumber + '\'' +
                ", sector='" + sector + '\'' +
                ", tribune='" + tribune + '\'' +
                '}';
    }
}

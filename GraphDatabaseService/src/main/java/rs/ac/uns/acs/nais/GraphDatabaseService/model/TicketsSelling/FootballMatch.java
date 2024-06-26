package rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Node("FootballMatch")
public class FootballMatch {

    @Id
    @GeneratedValue
    private Long id;

    private String footballMatchID;

    private Date startTime;

    private String opponentName;

    private String result;

    @Relationship(type = "HAS_RESERVATION", direction = Relationship.Direction.OUTGOING)
    private List<SeatReservation> seatReservations;

    public FootballMatch() {
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getOpponentName() {
        return opponentName;
    }

    public void setOpponentName(String opponentName) {
        this.opponentName = opponentName;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "\nFootballMatch{" +
                "id=" + id +
                ", footballMatchID='" + footballMatchID + '\'' +
                ", startTime=" + startTime +
                ", opponentName='" + opponentName + '\'' +
                ", result='" + result + '\'' +
                "}\n";
    }
}

package rs.ac.uns.acs.nais.GraphDatabaseService.dto.TicketsSelling;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;

import java.time.LocalDateTime;

public class FiveLatestFootballMatchesDTO {

    @Id
    @GeneratedValue
    private Long id;

    private String footballMatchID;

//    private LocalDateTime startTime;

    private String opponentName;

    private String result;

    private Integer reservedTicketsNumber;

    private Integer cancelledTicketsNumber;

    private Integer paidTicketsNumber;

    public FiveLatestFootballMatchesDTO() {
    }

    public FiveLatestFootballMatchesDTO(String footballMatchID, String opponentName, String result, Integer reservedTicketsNumber, Integer cancelledTicketsNumber, Integer paidTicketsNumber) {
        this.footballMatchID = footballMatchID;
        this.opponentName = opponentName;
        this.result = result;
        this.reservedTicketsNumber = reservedTicketsNumber;
        this.cancelledTicketsNumber = cancelledTicketsNumber;
        this.paidTicketsNumber = paidTicketsNumber;
    }

    public String getFootballMatchID() {
        return footballMatchID;
    }

    public void setFootballMatchID(String footballMatchID) {
        this.footballMatchID = footballMatchID;
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

    public Integer getReservedTicketsNumber() {
        return reservedTicketsNumber;
    }

    public void setReservedTicketsNumber(Integer reservedTicketsNumber) {
        this.reservedTicketsNumber = reservedTicketsNumber;
    }

    public Integer getCancelledTicketsNumber() {
        return cancelledTicketsNumber;
    }

    public void setCancelledTicketsNumber(Integer cancelledTicketsNumber) {
        this.cancelledTicketsNumber = cancelledTicketsNumber;
    }

    public Integer getPaidTicketsNumber() {
        return paidTicketsNumber;
    }

    public void setPaidTicketsNumber(Integer paidTicketsNumber) {
        this.paidTicketsNumber = paidTicketsNumber;
    }

    @Override
    public String toString() {
        return "FiveLatestFootballMatchesDTO{" +
                "footballMatchID='" + footballMatchID + '\'' +
                ", opponentName='" + opponentName + '\'' +
                ", result='" + result + '\'' +
                ", reservedTicketsNumber=" + reservedTicketsNumber +
                ", cancelledTicketsNumber=" + cancelledTicketsNumber +
                ", paidTicketsNumber=" + paidTicketsNumber +
                '}';
    }
}

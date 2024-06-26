package rs.ac.uns.acs.nais.GraphDatabaseService.dto.TicketsSelling;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class KafkaMatchToGraphdbDTO {
    @JsonProperty("footballMatchID")
    private String footballMatchID;
    @JsonProperty("startTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startTime;

    @JsonProperty("opponentName")
    private String opponentName;

    @JsonProperty("result")
    private String result;

    public KafkaMatchToGraphdbDTO() {
    }

    public KafkaMatchToGraphdbDTO(String footballMatchID, LocalDateTime startTime, String opponentName, String result) {
        this.footballMatchID = footballMatchID;
        this.startTime = startTime;
        this.opponentName = opponentName;
        this.result = result;
    }


    public String getFootballMatchID() {
        return footballMatchID;
    }

    public void setFootballMatchID(String footballMatchID) {
        this.footballMatchID = footballMatchID;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
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
}


package rs.ac.uns.acs.nais.ColumnarDatabaseService.dto;

import rs.ac.uns.acs.nais.ColumnarDatabaseService.enumeration.MatchType;

import java.time.LocalDateTime;

public class MatchRecordDTO {

    private LocalDateTime dateTimeStarted;
    private String opponentName;
    private String location;
    private String score;
    private MatchType type;

    public MatchRecordDTO() {
    }

    public MatchRecordDTO(LocalDateTime dateTimeStarted, String opponentName, String location, String score, MatchType type) {
        this.dateTimeStarted = dateTimeStarted;
        this.opponentName = opponentName;
        this.location = location;
        this.score = score;
        this.type = type;
    }

    public LocalDateTime getDateTimeStarted() {
        return dateTimeStarted;
    }

    public void setDateTimeStarted(LocalDateTime dateTimeStarted) {
        this.dateTimeStarted = dateTimeStarted;
    }

    public String getOpponentName() {
        return opponentName;
    }

    public void setOpponentName(String opponentName) {
        this.opponentName = opponentName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public MatchType getType() {
        return type;
    }

    public void setType(MatchType type) {
        this.type = type;
    }
}

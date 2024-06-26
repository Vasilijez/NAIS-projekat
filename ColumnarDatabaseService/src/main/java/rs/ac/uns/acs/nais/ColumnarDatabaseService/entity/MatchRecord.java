package rs.ac.uns.acs.nais.ColumnarDatabaseService.entity;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.enumeration.MatchType;

import java.time.LocalDateTime;
import java.util.UUID;

@Table("match_records")
public class MatchRecord {

    @PrimaryKeyColumn(name = "season", type = PrimaryKeyType.PARTITIONED)
    private Integer season;
    @PrimaryKeyColumn(name = "match_id", type = PrimaryKeyType.CLUSTERED)
    private UUID id;
    private LocalDateTime dateTimeStarted;
    private String opponentName;
    private String location;
    private String score;

    private MatchType type;

    public MatchRecord() {
    }

    public MatchRecord(UUID id, LocalDateTime dateTimeStarted, String opponentName, String location, String score, MatchType type) {
        this.id = id;
        this.dateTimeStarted = dateTimeStarted;
        this.opponentName = opponentName;
        this.location = location;
        this.score = score;
        this.season = dateTimeStarted.getYear();
        this.type = type;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getDateTimeStarted() {
        return dateTimeStarted;
    }

    public void setDateTimeStarted(LocalDateTime dateTimeStarted) {
        this.dateTimeStarted = dateTimeStarted;
        this.season = dateTimeStarted.getYear();
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

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public MatchType getType() {
        return type;
    }

    public void setType(MatchType type) {
        this.type = type;
    }
}


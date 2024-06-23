package rs.ac.uns.acs.nais.GraphDatabaseService.model;

import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@RelationshipProperties
public class Plays {

    @RelationshipId
    private Long id;

    @TargetNode
    private Match match;

    private int assists;

    private int goalsScored;

    private int minutesPlayed;

    private int foulsMade;

    public Plays(Long id, Match match, int assists, int goalsScored, int minutesPlayed, int foulsMade) {
        this.id = id;
        this.match = match;
        this.assists = assists;
        this.goalsScored = goalsScored;
        this.minutesPlayed = minutesPlayed;
        this.foulsMade = foulsMade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }

    public int getMinutesPlayed() {
        return minutesPlayed;
    }

    public void setMinutesPlayed(int minutesPlayed) {
        this.minutesPlayed = minutesPlayed;
    }

    public int getFoulsMade() {
        return foulsMade;
    }

    public void setFoulsMade(int foulsMade) {
        this.foulsMade = foulsMade;
    }
}

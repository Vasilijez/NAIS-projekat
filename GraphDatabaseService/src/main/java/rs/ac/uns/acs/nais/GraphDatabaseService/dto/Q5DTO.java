package rs.ac.uns.acs.nais.GraphDatabaseService.dto;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class Q5DTO {
    @Id
    @GeneratedValue
    private Long id;

    private String teamName;
    private double avgGoalsTop;
    private double avgMinutesTop;
    private double avgAssistsTop;
    private double avgGoalsOther;
    private double avgMinutesOther;
    private double avgAssistsOther;

    public Q5DTO(String teamName, double avgGoalsTop, double avgMinutesTop, double avgAssistsTop, double avgGoalsOther, double avgMinutesOther, double avgAssistsOther, Long id) {
        this.teamName = teamName;
        this.avgGoalsTop = avgGoalsTop;
        this.avgMinutesTop = avgMinutesTop;
        this.avgAssistsTop = avgAssistsTop;
        this.avgGoalsOther = avgGoalsOther;
        this.avgMinutesOther = avgMinutesOther;
        this.avgAssistsOther = avgAssistsOther;
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public double getAvgGoalsTop() {
        return avgGoalsTop;
    }

    public void setAvgGoalsTop(double avgGoalsTop) {
        this.avgGoalsTop = avgGoalsTop;
    }

    public double getAvgMinutesTop() {
        return avgMinutesTop;
    }

    public void setAvgMinutesTop(double avgMinutesTop) {
        this.avgMinutesTop = avgMinutesTop;
    }

    public double getAvgAssistsTop() {
        return avgAssistsTop;
    }

    public void setAvgAssistsTop(double avgAssistsTop) {
        this.avgAssistsTop = avgAssistsTop;
    }

    public double getAvgGoalsOther() {
        return avgGoalsOther;
    }

    public void setAvgGoalsOther(double avgGoalsOther) {
        this.avgGoalsOther = avgGoalsOther;
    }

    public double getAvgMinutesOther() {
        return avgMinutesOther;
    }

    public void setAvgMinutesOther(double avgMinutesOther) {
        this.avgMinutesOther = avgMinutesOther;
    }

    public double getAvgAssistsOther() {
        return avgAssistsOther;
    }

    public void setAvgAssistsOther(double avgAssistsOther) {
        this.avgAssistsOther = avgAssistsOther;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

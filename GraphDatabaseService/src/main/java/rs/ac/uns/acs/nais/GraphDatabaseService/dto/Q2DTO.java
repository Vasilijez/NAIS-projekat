package rs.ac.uns.acs.nais.GraphDatabaseService.dto;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class Q2DTO {
    @Id
    @GeneratedValue
    private Long id;

    private double avgGoalsLiked;
    private double avgGoalsUnliked;

    public Q2DTO(double avgGoalsLiked, double avgGoalsUnliked, Long id) {
        this.avgGoalsLiked = avgGoalsLiked;
        this.avgGoalsUnliked = avgGoalsUnliked;
        this.id=id;
    }

    public double getAvgGoalsLiked() {
        return avgGoalsLiked;
    }

    public void setAvgGoalsLiked(double avgGoalsLiked) {
        this.avgGoalsLiked = avgGoalsLiked;
    }

    public double getAvgGoalsUnliked() {
        return avgGoalsUnliked;
    }

    public void setAvgGoalsUnliked(double avgGoalsUnliked) {
        this.avgGoalsUnliked = avgGoalsUnliked;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

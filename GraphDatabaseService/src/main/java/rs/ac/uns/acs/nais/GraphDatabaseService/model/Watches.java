package rs.ac.uns.acs.nais.GraphDatabaseService.model;

import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@RelationshipProperties
public class Watches {

    @RelationshipId
    private Long id;

    @TargetNode
    private Match match;

    // vrednosit [1-5]
    private int enjoyment;

    public Watches(Long id, Match match, int enjoyment) {
        this.id = id;
        this.match = match;
        this.enjoyment = enjoyment;
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

    public int getEnjoyment() {
        return enjoyment;
    }

    public void setEnjoyment(int enjoyment) {
        this.enjoyment = enjoyment;
    }
}

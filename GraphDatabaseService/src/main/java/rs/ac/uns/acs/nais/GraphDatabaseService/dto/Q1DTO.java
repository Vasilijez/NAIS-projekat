package rs.ac.uns.acs.nais.GraphDatabaseService.dto;


import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class Q1DTO {
    @Id
    @GeneratedValue
    private Long id;

    private int numWins;
    private int numLosses;

    public Q1DTO(int numWins, int numLosses, Long id) {
        this.numWins = numWins;
        this.numLosses = numLosses;
        this.id = id;
    }

    public int getNumWins() {
        return numWins;
    }

    public void setNumWins(int numWins) {
        this.numWins = numWins;
    }

    public int getNumLosses() {
        return numLosses;
    }

    public void setNumLosses(int numLosses) {
        this.numLosses = numLosses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}


package rs.ac.uns.acs.nais.GraphDatabaseService.dto;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class Q4DTO {

    @Id
    @GeneratedValue
    private Long id;

    private String playerName;
    private int playerAge;
    private int numFans;

    public Q4DTO(String playerName, int playerAge, int numFans, Long id) {
        this.playerName = playerName;
        this.playerAge = playerAge;
        this.numFans = numFans;
        this.id = id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getPlayerAge() {
        return playerAge;
    }

    public void setPlayerAge(int playerAge) {
        this.playerAge = playerAge;
    }

    public int getNumFans() {
        return numFans;
    }

    public void setNumFans(int numFans) {
        this.numFans = numFans;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

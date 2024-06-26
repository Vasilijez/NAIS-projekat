package rs.ac.uns.acs.nais.GraphDatabaseService.dto;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class Q3DTO {
    @Id
    @GeneratedValue
    private Long id;

    private String playerName;
    private String position;
    private String teamName;

    public Q3DTO(String playerName, String position, String teamName, Long id) {
        this.playerName = playerName;
        this.position = position;
        this.teamName = teamName;
        this.id = id;
    }

    public Q3DTO(String playerName, String position, String teamName) {
        this.playerName = playerName;
        this.position = position;
        this.teamName = teamName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

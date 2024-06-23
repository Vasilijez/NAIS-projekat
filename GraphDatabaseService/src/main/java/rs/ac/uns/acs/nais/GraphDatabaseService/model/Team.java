package rs.ac.uns.acs.nais.GraphDatabaseService.model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;

@Node
public class Team {
    @Id
    @GeneratedValue
    private Long idOriginal;

    private String name;

    @Relationship(value = "PARTICIPATED", direction = Relationship.Direction.OUTGOING)
    private List<Participates> participated = new ArrayList<>();

    public Team(Long idOriginal, String name) {
        this.idOriginal = idOriginal;
        this.name = name;
    }

    public Long getIdOriginal() {
        return idOriginal;
    }

    public void setIdOriginal(Long idOriginal) {
        this.idOriginal = idOriginal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Participates> getParticipated() {
        return participated;
    }

    public void setParticipated(List<Participates> participated) {
        this.participated = participated;
    }
}

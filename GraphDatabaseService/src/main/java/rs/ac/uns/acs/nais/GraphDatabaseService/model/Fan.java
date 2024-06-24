package rs.ac.uns.acs.nais.GraphDatabaseService.model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class Fan {
    @Id
    @GeneratedValue
    private Long idOriginal;

    private String name;

    //TODO koji mec je gledao
    //TODO koji tim voli? za koji navija?
    //TODO koji igrac mu je omiljeni


    public Fan(Long idOriginal, String name) {
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
}

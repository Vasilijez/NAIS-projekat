package rs.ac.uns.acs.nais.GraphDatabaseService.model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;

@Node
public class Player {
    @Id
    @GeneratedValue
    private Long idOriginal;

    private String name;

    private String lastName;

    private String position;

    private int age;

//    private Team team;

    @Relationship(value = "PLAYED", direction = Relationship.Direction.OUTGOING)
    private List<Plays> played = new ArrayList<>();//TODO geteri itd

    public Player(Long idOriginal, String name, String lastName, String position, int age) {
        this.idOriginal = idOriginal;
        this.name = name;
        this.lastName = lastName;
        this.position = position;
        this.age = age;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

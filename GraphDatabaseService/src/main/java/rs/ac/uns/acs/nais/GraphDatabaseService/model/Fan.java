package rs.ac.uns.acs.nais.GraphDatabaseService.model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;

@Node
public class Fan {
    @Id
    @GeneratedValue
    private Long idOriginal;

    private String name;

    @Relationship(value = "WATCHED", direction = Relationship.Direction.OUTGOING)
    private List<Watches> watched = new ArrayList<>();

    @Relationship(value = "CHEERS", direction = Relationship.Direction.OUTGOING)
    private List<Cheers> cheered = new ArrayList<>();

    @Relationship(value = "LIKES", direction = Relationship.Direction.OUTGOING)
    private List<Likes> liked = new ArrayList<>();


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

    public List<Watches> getWatched() {
        return watched;
    }

    public void setWatched(List<Watches> watched) {
        this.watched = watched;
    }

    public List<Cheers> getCheered() {
        return cheered;
    }

    public void setCheered(List<Cheers> cheered) {
        this.cheered = cheered;
    }

    public List<Likes> getLiked() {
        return liked;
    }

    public void setLiked(List<Likes> liked) {
        this.liked = liked;
    }
}

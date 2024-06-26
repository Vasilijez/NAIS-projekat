package rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.time.LocalDate;
import java.util.List;

@Node("Fan")
public class Fan {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String fanType;

    private String favouritePlayer;

    private LocalDate dateOfBirth;

    private String gender;

    private String level;

    @Relationship(type = "CREATES_ORDER", direction = Relationship.Direction.OUTGOING)
    private List<Order> orders;

    @Relationship(type = "MEMBER_OF", direction = Relationship.Direction.OUTGOING)
    private LoyaltyProgram loyaltyProgram;

    public Fan() {
    }

    public Fan(Long id, String username, String fanType, String favouritePlayer, LocalDate dateOfBirth, String gender, String level) {
        this.id = id;
        this.username = username;
        this.fanType = fanType;
        this.favouritePlayer = favouritePlayer;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.level = level;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFanType() {
        return fanType;
    }

    public void setFanType(String fanType) {
        this.fanType = fanType;
    }

    public String getFavouritePlayer() {
        return favouritePlayer;
    }

    public void setFavouritePlayer(String favouritePlayer) {
        this.favouritePlayer = favouritePlayer;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "\nFan {" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", fanType='" + fanType + '\'' +
                ", favouritePlayer='" + favouritePlayer + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender='" + gender + '\'' +
                ", level='" + level + '\'' +
                '}' + '\n';
    }
}

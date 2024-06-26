package rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import java.util.ArrayList;
import java.util.List;
@Node("LoyaltyProgram")
public class LoyaltyProgram {

    @Id @GeneratedValue
    private Long id;

    private String level;

    private Double discountRate;

    public LoyaltyProgram() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(Double discountRate) {
        this.discountRate = discountRate;
    }

    @Override
    public String toString() {
        return "LoyaltyProgram {" +
                "id=" + id +
                ", level='" + level + '\'' +
                ", discountRate=" + discountRate +
                '}';
    }
}

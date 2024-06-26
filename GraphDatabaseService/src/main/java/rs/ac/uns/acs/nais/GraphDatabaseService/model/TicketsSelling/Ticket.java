package rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node("Ticket")
public class Ticket {

    @Id
    @GeneratedValue
    private Long id;

    private String ticketID;

    private Double price;

    private String orderID;

    public Ticket() {
    }

    public Ticket(Long id, String ticketID, Double price, String orderID) {
        this.id = id;
        this.ticketID = ticketID;
        this.price = price;
        this.orderID = orderID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTicketID() {
        return ticketID;
    }

    public void setTicketID(String ticketID) {
        this.ticketID = ticketID;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    @Override
    public String toString() {
        return "Ticket {" +
                "id=" + id +
                ", ticketID='" + ticketID + '\'' +
                ", price=" + price +
                ", orderID='" + orderID + '\'' +
                '}';
    }
}

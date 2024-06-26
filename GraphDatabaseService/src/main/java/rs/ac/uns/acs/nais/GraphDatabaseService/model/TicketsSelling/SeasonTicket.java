package rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.time.LocalDate;

@Node("SeasonTicket")
public class SeasonTicket {

    @Id
    @GeneratedValue
    private Long id;

    private String ticketID;

    private Double price;

    private LocalDate startTime;

    private LocalDate endTime;

    private String orderID;

    public SeasonTicket() {
    }

    public SeasonTicket(Long id, String ticketID, Double price, LocalDate startTime, LocalDate endTime, String orderID) {
        this.id = id;
        this.ticketID = ticketID;
        this.price = price;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    @Override
    public String toString() {
        return "SeasonTicket {" +
                "id=" + id +
                ", ticketID='" + ticketID + '\'' +
                ", price=" + price +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", orderID='" + orderID + '\'' +
                '}';
    }
}

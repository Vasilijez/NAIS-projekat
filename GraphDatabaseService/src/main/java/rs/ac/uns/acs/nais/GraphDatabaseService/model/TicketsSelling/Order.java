package rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.time.LocalDate;
import java.util.Date;

@Node("Order")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    private String orderID;

    private Double totalPrice;

    private LocalDate creationDate;

    private String deliveryType;

    private String status;

    private String paymentMethod;

    private String username;

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "\n Order {" +
                "id=" + id +
                ", orderID='" + orderID + '\'' +
                ", totalPrice=" + totalPrice +
                ", creationDate=" + creationDate +
                ", deliveryType='" + deliveryType + '\'' +
                ", status='" + status + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", username='" + username + '\'' +
                '}' + '\n';
    }
}

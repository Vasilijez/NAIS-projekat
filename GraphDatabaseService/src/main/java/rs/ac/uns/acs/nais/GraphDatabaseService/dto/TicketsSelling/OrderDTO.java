package rs.ac.uns.acs.nais.GraphDatabaseService.dto.TicketsSelling;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.time.LocalDate;

public class OrderDTO {

    private String orderID;

    private Double totalPrice;

    private LocalDate creationDate;

    private String deliveryType;

    private String status;

    private String paymentMethod;

    public OrderDTO(String orderID, Double totalPrice, LocalDate creationDate, String deliveryType, String status, String paymentMethod) {
        this.orderID = orderID;
        this.totalPrice = totalPrice;
        this.creationDate = creationDate;
        this.deliveryType = deliveryType;
        this.status = status;
        this.paymentMethod = paymentMethod;
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

    @Override
    public String toString() {
        return "Order {" +
                ", orderID='" + orderID + '\'' +
                ", totalPrice=" + totalPrice +
                ", creationDate='" + creationDate + '\'' +
                ", deliveryType='" + deliveryType + '\'' +
                ", status='" + status + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                '}';
    }
}

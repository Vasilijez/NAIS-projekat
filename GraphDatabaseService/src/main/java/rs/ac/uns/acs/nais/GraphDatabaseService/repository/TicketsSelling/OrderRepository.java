package rs.ac.uns.acs.nais.GraphDatabaseService.repository.TicketsSelling;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.LoyaltyProgram;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.Order;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends Neo4jRepository<Order, Long> {

    @Query("CREATE (o:Order {orderID: $orderID, totalPrice: $totalPrice, creationDate: $creationDate, deliveryType: $deliveryType, status: $status, paymentMethod: $paymentMethod, username: $username }) " +
            "RETURN o ")
    Order createOrder(String orderID, Double totalPrice, LocalDate creationDate, String deliveryType, String status, String paymentMethod, String username);

    @Query("MATCH (o:Order {orderID: $orderID})  " +
            "RETURN o ")
    Order findOrder(String orderID);

    @Query("MATCH (o:Order {orderID: $orderID})  " +
            "SET o.orderID = $updatingOrderID, o.totalPrice = $totalPrice, o.creationDate = $creationDate, o.deliveryType = $deliveryType, o.status = $status, o.paymentMethod = $paymentMethod, o.username = $username  " +
            "RETURN o as order ")
    Order updateOrder(String orderID, String updatingOrderID, Double totalPrice, LocalDate creationDate, String deliveryType, String status, String paymentMethod, String username);

    @Query("MATCH (o:Order {orderID: $orderID})  " +
            "DETACH DELETE o " +
            "RETURN count(o) > 0 ")
    boolean deleteOrder(String orderID);


    @Query("MATCH (f:Fan)-[:CREATES_ORDER]->(o:Order) " +
            "WHERE o.paymentMethod = \"Cash\" and f.fanType = \"Student\" and o.status = \"Shipped\"  " +
            "RETURN o " +
            "ORDER BY o.totalPrice DESC  " +
            "LIMIT 3 ")
    List<Order> findTop3ShippedAndCashPaidOrdersMadeByStudents();


    @Query("MATCH (:Fan {username: $username})-[r:CREATES_ORDER]->(o:Order) " +
            "RETURN o ")
    List<Order> findCreatesOrderRelationships(String username);


}

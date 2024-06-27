package rs.ac.uns.acs.nais.GraphDatabaseService.service.TicketsSelling;

import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.LoyaltyProgram;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.Order;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.TicketsSelling.LoyaltyProgramRepository;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.TicketsSelling.OrderRepository;

import java.text.SimpleDateFormat;
import java.util.List;


@Service
public class OrderService {

    private OrderRepository orderRepository;

//    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(Order order) {
//        System.out.println("USAO U CREATE ORDER I DATUM IZGLEDA OVAKO " + formatter.format(order.getCreationDate()));

        return orderRepository.createOrder(order.getOrderID(), order.getTotalPrice(), order.getCreationDate(), order.getDeliveryType(), order.getStatus(), order.getPaymentMethod(), order.getUsername());
    }

    public Order findOrder(String orderID) {
        return orderRepository.findOrder(orderID);
    }

    public Order updateOrder(String orderID, Order order) {
        return orderRepository.updateOrder(orderID, order.getOrderID(), order.getTotalPrice(), order.getCreationDate(), order.getDeliveryType(), order.getStatus(), order.getPaymentMethod(), order.getUsername());
    }

    public boolean deleteOrder(String orderID) {
        return orderRepository.deleteOrder(orderID);
    }

    public List<Order> findTop3ShippedAndCashPaidOrdersMadeByStudents() {
        return orderRepository.findTop3ShippedAndCashPaidOrdersMadeByStudents();
    }

    public List<Order> findCreatesOrderRelationships(String username) {
        return orderRepository.findCreatesOrderRelationships(username);
    }

}

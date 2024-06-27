package rs.ac.uns.acs.nais.GraphDatabaseService.controller.TicketsSelling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.LoyaltyProgram;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.Order;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.TicketsSelling.LoyaltyProgramService;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.TicketsSelling.OrderService;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(value = "", consumes = "application/json")
    public ResponseEntity<String> createOrder(@RequestBody Order order){
        Order created = orderService.createOrder(order);
        if (created != null) {
            return ResponseEntity.ok("Order is created: " + created.toString());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong!");
        }
    }

    @GetMapping("/{orderID}")
    public ResponseEntity<String> findOrder(@PathVariable String orderID) {
        Order order = orderService.findOrder(orderID);
        if (order != null) {
            return ResponseEntity.ok("Order found: " + order.toString());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order with order id '" + orderID + "' not found.");
        }
    }

    @PutMapping("/{orderID}")
    public ResponseEntity<String> updateOrder(@PathVariable String orderID, @RequestBody Order order) {
        Order updatedNode = orderService.updateOrder(orderID, order);
        if (updatedNode != null) {
            return ResponseEntity.ok("Order updated successfully: " + updatedNode.toString());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong!");
        }
    }

    @DeleteMapping("/{orderID}")
    public ResponseEntity<String> deleteOrder(@PathVariable String orderID){
        if (orderService.deleteOrder(orderID)) {
            return ResponseEntity.ok("Order with order id '" + orderID + "' was successfully deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order with order id '" + orderID + "' not found.");
        }
    }

    @GetMapping("/findTop3ShippedAndCashPaidOrdersMadeByStudents")
    public ResponseEntity<String> findTop3ShippedAndCashPaidOrdersMadeByStudents() {
        List<Order> orders = orderService.findTop3ShippedAndCashPaidOrdersMadeByStudents();
        if (orders != null) {
            return ResponseEntity.ok("Orders are found: " + orders.toString());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Orders are not found.");
        }
    }

    @GetMapping("/creates-order/{username}")
    public ResponseEntity<String> findCreatesOrderRelationships(@PathVariable String username) {
        List<Order> orders = orderService.findCreatesOrderRelationships(username);
        if (orders != null) {
            return ResponseEntity.ok("Fan with username '" + username + "' has the following orders: " + orders.toString());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fan with username '" + username + "' doesn't have orders.");
        }
    }

}


package rs.ac.uns.acs.nais.GraphDatabaseService.controller.TicketsSelling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.TicketsSelling.*;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.Fan;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.Order;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.TicketsSelling.FanService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/fan")
public class FanController {

    @Autowired
    private FanService fanService;

    @PostMapping(value = "", consumes = "application/json")
    public ResponseEntity<String> createFan(@RequestBody Fan fan){
        Fan created = fanService.createFan(fan);
        if (created != null) {
            return ResponseEntity.ok("Fan is created: " + created.toString());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong!");
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<String> findFan(@PathVariable String username) {
        Fan order = fanService.findFan(username);
        if (order != null) {
            return ResponseEntity.ok("Fan found: " + order.toString());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fan with username '" + username + "' not found.");
        }
    }

    @PutMapping(value = "/{username}", consumes = "application/json")
    public ResponseEntity<String> updateFan(@PathVariable String username, @RequestBody Fan fan) {
        Fan updatedNode = fanService.updateFan(username, fan);
        if (updatedNode != null) {
            return ResponseEntity.ok("Fan updated successfully: " + updatedNode.toString());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong!");
        }
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<String> deleteFan(@PathVariable String username){
        if (fanService.deleteFan(username)) {
            return ResponseEntity.ok("Fan with username '" + username + "' was successfully deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fan with username '" + username + "' not found.");
        }
    }

    @PostMapping(value = "/creates-order/{username}/{orderID}")
    public ResponseEntity<String> createCreatesOrderRelationship(@PathVariable String username, @PathVariable String orderID) {
        if (fanService.createCreatesOrderRelationship(username, orderID)) {
            return ResponseEntity.ok("Relationship between username '" + username + "' and orderID '" + orderID + "' was successfully created.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Relationship between username '" + username + "' and orderID '" + orderID + "' wasn't successfully created.");
        }
    }

    // TODO: Ne radi, verovatno problem u datumu nekako ne moze da ga prepozna, jer je sve isto kao kod Pantica kao kad vraca npr. listu playlista, model je identican, logika povratne vrednosti upita je ista.
    @GetMapping("/creates-order/{username}")
    public ResponseEntity<String> findCreatesOrderRelationships(@PathVariable String username) {
        List<Order> orders = fanService.findCreatesOrderRelationships(username);
        if (orders != null) {
            return ResponseEntity.ok("Fan with username '" + username + "' has the following orders: " + orders.toString());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fan with username '" + username + "' doesn't have orders.");
        }
    }

    @GetMapping("/findTwoFansWithLowestPointsAndAtLeastOneOrderThisYear")
    public ResponseEntity<String> findTwoFansWithLowestPointsAndAtLeastOneOrderThisYear() {
        List<TwoBottomFansDTO> nodes = fanService.findTwoFansWithLowestPointsAndAtLeastOneOrderThisYear();
        if (nodes != null) {
            System.out.println("Fans are found!");
            return ResponseEntity.ok("Fans are found " + nodes.toString());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fans are not found " + nodes.toString());
        }
    }







    @PutMapping(value = "/updateFanLoyaltyProgramPoints")
    public ResponseEntity<String> updateFanLoyaltyProgramPoints() {
        List <FanLoyaltyProgramPointsUpdatingDTO> updatedNodes = fanService.updateFanLoyaltyProgramPoints();
        if (updatedNodes != null) {
            return ResponseEntity.ok("Fan loyalty program points have been updated successfully: " + updatedNodes.toString());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong!");
        }
    }

}


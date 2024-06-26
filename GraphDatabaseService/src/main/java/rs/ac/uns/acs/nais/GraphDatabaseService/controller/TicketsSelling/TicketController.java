package rs.ac.uns.acs.nais.GraphDatabaseService.controller.TicketsSelling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.Seat;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.Ticket;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.TicketsSelling.SeatService;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.TicketsSelling.TicketService;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping(value = "", consumes = "application/json")
    public ResponseEntity<String> createTicket(@RequestBody Ticket ticket) {
        Ticket createdNode = ticketService.createTicket(ticket);
        if (createdNode != null) {
            return ResponseEntity.ok("Ticket is created: " + createdNode.toString());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong!");
        }
    }

    @GetMapping("/{ticketID}")
    public ResponseEntity<String> findTicket(@PathVariable String ticketID) {
        Ticket node = ticketService.findTicket(ticketID);
        if (node != null) {
            return ResponseEntity.ok("Ticket found: " + node.toString());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ticket with ticket id '" + ticketID + "' not found.");
        }
    }

    @PutMapping(value = "/{ticketID}", consumes = "application/json")
    public ResponseEntity<String> updateTicket(@PathVariable String ticketID, @RequestBody Ticket ticket) {
        Ticket updatedNode = ticketService.updateTicket(ticketID, ticket);
        if (updatedNode != null) {
            return ResponseEntity.ok("Ticket updated successfully: " + updatedNode.toString());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong!");
        }
    }

    @DeleteMapping("/{ticketID}")
    public ResponseEntity<String> deleteTicket(@PathVariable String ticketID){
        if (ticketService.deleteTicket(ticketID)) {
            return ResponseEntity.ok("Ticket with ticket id '" + ticketID + "' was successfully deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ticket with ticket id '" + ticketID + "' not found.");
        }
    }

}


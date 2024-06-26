package rs.ac.uns.acs.nais.GraphDatabaseService.controller.TicketsSelling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.SeasonTicket;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.Ticket;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.TicketsSelling.SeasonTicketRepository;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.TicketsSelling.SeasonTicketService;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.TicketsSelling.TicketService;

@RestController
@RequestMapping("/season-ticket")
public class SeasonTicketController {

    @Autowired
    private SeasonTicketService seasonTicketService;

    @PostMapping(value = "", consumes = "application/json")
    public ResponseEntity<String> createSeasonTicket(@RequestBody SeasonTicket seasonTicket) {
        SeasonTicket createdNode = seasonTicketService.createSeasonTicket(seasonTicket);
        if (createdNode != null) {
            return ResponseEntity.ok("Ticket is created: " + createdNode.toString());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong!");
        }
    }

    @GetMapping("/{ticketID}")
    public ResponseEntity<String> findSeasonTicket(@PathVariable String ticketID) {
        SeasonTicket node = seasonTicketService.findSeasonTicket(ticketID);
        if (node != null) {
            return ResponseEntity.ok("Ticket found: " + node.toString());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ticket with ticket id '" + ticketID + "' not found.");
        }
    }

    @PutMapping(value = "/{ticketID}", consumes = "application/json")
    public ResponseEntity<String> updateSeasonTicket(@PathVariable String ticketID, @RequestBody SeasonTicket seasonTicket) {
        SeasonTicket updatedNode = seasonTicketService.updateSeasonTicket(ticketID, seasonTicket);
        if (updatedNode != null) {
            return ResponseEntity.ok("Ticket updated successfully: " + updatedNode.toString());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong!");
        }
    }

    @DeleteMapping("/{ticketID}")
    public ResponseEntity<String> deleteSeasonTicket(@PathVariable String ticketID){
        if (seasonTicketService.deleteSeasonTicket(ticketID)) {
            return ResponseEntity.ok("Ticket with ticket id '" + ticketID + "' was successfully deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ticket with ticket id '" + ticketID + "' not found.");
        }
    }

}


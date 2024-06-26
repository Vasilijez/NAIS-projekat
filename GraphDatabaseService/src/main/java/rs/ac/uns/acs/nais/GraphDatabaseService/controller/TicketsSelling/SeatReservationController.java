package rs.ac.uns.acs.nais.GraphDatabaseService.controller.TicketsSelling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.Fan;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.Order;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.SeatReservation;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.TicketsSelling.FanService;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.TicketsSelling.SeatReservationService;

import java.util.List;

@RestController
@RequestMapping("/seat-reservation")
public class SeatReservationController {

    @Autowired
    private SeatReservationService seatReservationService;

    @PostMapping(value = "", consumes = "application/json")
    public ResponseEntity<String> createSeatReservation(@RequestBody SeatReservation seatReservation){
        SeatReservation createdNode = seatReservationService.createSeatReservation(seatReservation);
        if (createdNode != null) {
            return ResponseEntity.ok("Seat reservation is created: " + createdNode.toString());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong!");
        }
    }

    @GetMapping("/{footballMatchID}/{seatNumber}")
    public ResponseEntity<String> findSeatReservation(@PathVariable String footballMatchID, @PathVariable String seatNumber) {
        SeatReservation node = seatReservationService.findSeatReservation(footballMatchID, seatNumber);
        if (node != null) {
            return ResponseEntity.ok("Seat reservation found: " + node.toString());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Seat reservation with football match id '" + footballMatchID + "' and seat number '" + seatNumber + "' not found.");
        }
    }

    @PutMapping(value = "/{footballMatchID}/{seatNumber}", consumes = "application/json")
    public ResponseEntity<String> updateSeatReservation(@PathVariable String footballMatchID, @PathVariable String seatNumber, @RequestBody SeatReservation seatReservation) {
        SeatReservation updatedNode = seatReservationService.updateSeatReservation(footballMatchID, seatNumber, seatReservation);
        if (updatedNode != null) {
            return ResponseEntity.ok("Seat reservation updated successfully: " + updatedNode.toString());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong!");
        }
    }

    @DeleteMapping("/{footballMatchID}/{seatNumber}")
    public ResponseEntity<String> deleteSeatReservation(@PathVariable String footballMatchID, @PathVariable String seatNumber){
        if (seatReservationService.deleteSeatReservation(footballMatchID, seatNumber)) {
            return ResponseEntity.ok("Seat reservation with football match id '" + footballMatchID + "' and seat number '" + seatNumber + "' was successfully deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Seat reservation with football match id '" + footballMatchID + "' and seat number '" + seatNumber + "' not found.");
        }
    }

}


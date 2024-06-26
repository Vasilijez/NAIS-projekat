package rs.ac.uns.acs.nais.GraphDatabaseService.controller.TicketsSelling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.TicketsSelling.RecommendedSeasonTicketSeatsDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.TicketsSelling.RecommendedStandardTicketSeatsDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.TicketsSelling.SeasonTicketStartTimeDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.Seat;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.SeatReservation;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.TicketsSelling.SeatReservationService;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.TicketsSelling.SeatService;

import java.util.List;

@RestController
@RequestMapping("/seat")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @PostMapping(value = "", consumes = "application/json")
    public ResponseEntity<String> createSeat(@RequestBody Seat seat) {
        Seat createdNode = seatService.createSeat(seat);
        if (createdNode != null) {
            return ResponseEntity.ok("Seat is created: " + createdNode.toString());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong!");
        }
    }

    @GetMapping("/{seatNumber}")
    public ResponseEntity<String> findSeat(@PathVariable String seatNumber) {
        Seat node = seatService.findSeat(seatNumber);
        if (node != null) {
            return ResponseEntity.ok("Seat found: " + node.toString());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Seat with seat number '" + seatNumber + "' not found.");
        }
    }

    @PutMapping(value = "/{seatNumber}", consumes = "application/json")
    public ResponseEntity<String> updateSeat(@PathVariable String seatNumber, @RequestBody Seat seat) {
        Seat updatedNode = seatService.updateSeat(seatNumber, seat);
        if (updatedNode != null) {
            return ResponseEntity.ok("Seat updated successfully: " + updatedNode.toString());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong!");
        }
    }

    @DeleteMapping("/{seatNumber}")
    public ResponseEntity<String> deleteSeat(@PathVariable String seatNumber){
        if (seatService.deleteSeat(seatNumber)) {
            return ResponseEntity.ok("Seat with seat number '" + seatNumber + "' was successfully deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Seat with seat number '" + seatNumber + "' not found.");
        }
    }

    @GetMapping("/recommendStandardTicketSeat/{fanUsername}")
    public ResponseEntity<String> recommendStandardTicketSeat(@PathVariable String fanUsername) {
        List<RecommendedStandardTicketSeatsDTO> nodes = seatService.recommendStandardTicketSeat(fanUsername);
        if (nodes != null) {
            return ResponseEntity.ok("Recommended seats are found " + nodes.toString());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recommended seats are not found " + nodes.toString());
        }
    }

    // Todo vrlo moguce da ce me datum zezati
    @GetMapping(value = "/recommendSeasonTicketSeat/{fanUsername}", consumes = "application/json")
    public ResponseEntity<String> recommendSeasonTicketSeat(@PathVariable String fanUsername, @RequestBody SeasonTicketStartTimeDTO seasonTicketStartTime) {
        List<RecommendedSeasonTicketSeatsDTO> nodes = seatService.recommendSeasonTicketSeat(fanUsername, seasonTicketStartTime);
        if (nodes != null) {
            return ResponseEntity.ok("Recommended seats are found " + nodes.toString());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recommended seats are not found " + nodes.toString());
        }
    }
}


package rs.ac.uns.acs.nais.GraphDatabaseService.controller.TicketsSelling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.LoyaltyProgram;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.Order;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.TransactionStatus;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.TicketsSelling.LoyaltyProgramService;

import javax.swing.text.StyledEditorKit;
import java.util.List;

@RestController
@RequestMapping("/loyalty-program")
public class LoyaltyProgramController {

    @Autowired
    private LoyaltyProgramService loyaltyProgramService;

    @PostMapping("")
    public ResponseEntity<String> createLoyaltyProgram(@RequestBody LoyaltyProgram loyaltyProgram){
        LoyaltyProgram created = loyaltyProgramService.createLoyaltyProgram(loyaltyProgram);
        if (created != null) {
            return ResponseEntity.ok("Loyalty Program is created: " + created.toString());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong!");
        }
    }

    @PostMapping("/saga")
    public ResponseEntity<String> createLoyaltyProgramSAGA(@RequestBody LoyaltyProgram loyaltyProgram){
        String requestMessage = loyaltyProgramService.createLoyaltyProgramSAGA(loyaltyProgram);
        return ResponseEntity.ok(requestMessage);
    }

    @GetMapping("/saga/checkStatus/{requestId}")
    public ResponseEntity<String> checkStatus(@PathVariable String requestId){
        TransactionStatus transactionStatus = loyaltyProgramService.checkStatus(requestId);
        return ResponseEntity.ok(transactionStatus.toString());
    }

    @GetMapping("/{level}")
    public ResponseEntity<String> getLoyaltyProgramByLevel(@PathVariable String level) {
        LoyaltyProgram loyaltyProgram = loyaltyProgramService.getLoyaltyProgramByLevel(level);
        if (loyaltyProgram != null) {
            return ResponseEntity.ok("Loyalty Program found: " + loyaltyProgram.toString());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Loyalty Program with level '" + level + "' not found.");
        }
    }

    @PutMapping("/{level}")
    public ResponseEntity<String> updateLoyaltyProgram(@PathVariable String level, @RequestBody LoyaltyProgram loyaltyProgram) {
        LoyaltyProgram updatedNode = loyaltyProgramService.updateLoyaltyProgram(level, loyaltyProgram);
        if (updatedNode != null) {
            return ResponseEntity.ok("Loyalty Program updated successfully: " + updatedNode.toString());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong!");
        }
    }

    @DeleteMapping("/{level}")
    public ResponseEntity<String> deleteLoyaltyProgram(@PathVariable String level){
        if (loyaltyProgramService.deleteLoyaltyProgram(level)) {
            return ResponseEntity.ok("Loyalty Program with level '" + level + "' was successfully deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Loyalty Program with level '" + level + "' not found.");
        }
    }

    @GetMapping("/member-of/{username}")
    public ResponseEntity<String> findMemberOfRelationship(@PathVariable String username) {
        List<LoyaltyProgram> loyaltyPrograms = loyaltyProgramService.findMemberOfRelationship(username);
        if (loyaltyPrograms != null) {
            return ResponseEntity.ok("Fan with username '" + username + "' is enrolled in the following loyalty programs: " + loyaltyPrograms.toString());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("He isn't enrolled in any loyalty program.");
        }
    }


}


package rs.ac.uns.acs.nais.GraphDatabaseService.controller.TicketsSelling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.LoyaltyProgram;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.TransactionStatus;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.TicketsSelling.LoyaltyProgramService;

import javax.swing.text.StyledEditorKit;

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


//    @PutMapping(value = "/{level}", consumes = "application/json")
//    public ResponseEntity<LoyaltyProgram> getLoyaltyProgramByLevel(@PathVariable String level, @RequestBody String updatingLevel, @RequestBody Double discountRate) {
//        LoyaltyProgram updatedNode = loyaltyProgramService.updateLoyaltyProgram(level, updatingLevel, discountRate);
//        return updatedNode != null ? ResponseEntity.ok(updatedNode) : ResponseEntity.notFound().build();
//    }


//    @PostMapping
//    public ResponseEntity<Customer> addNewCustomer(@RequestBody Customer customer){
//        Customer createdCustomer = customerService.addNewCustomer(customer);
//        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
//    }
//
//    @DeleteMapping
//    public ResponseEntity<Customer> deleteCustomer(@RequestParam String customerEmail){
//        if(customerService.deleteCustomer(customerEmail)){
//            return new ResponseEntity<>(HttpStatus.OK);
//        }
//
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//    @PutMapping
//    public ResponseEntity<Customer> updateCustomer(@RequestParam("customerEmailOld") String customerEmailOld, @RequestParam("customerEmailNew") String customerEmailNew ){
//        if(customerService.updateCustomer(customerEmailOld, customerEmailNew)){
//            return new ResponseEntity<>(HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//    @PostMapping("addReview")
//    public ResponseEntity addNewReview(@RequestBody ReviewDTO reviewDTO){
//        if(customerService.addReview(reviewDTO) != null){
//            return new ResponseEntity<>(HttpStatus.CREATED);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//    @PostMapping("addPurchase")
//    public ResponseEntity<Customer> addNewPurchase(@RequestBody OrderDTO orderDTO){
//        customerService.addPurchase(orderDTO);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }



}


package rs.ac.uns.acs.nais.RelationalDatabaseService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.RelationalDatabaseService.model.LoyaltyProgram;
import rs.ac.uns.acs.nais.RelationalDatabaseService.model.User;
import rs.ac.uns.acs.nais.RelationalDatabaseService.service.LoyaltyProgramService;
import rs.ac.uns.acs.nais.RelationalDatabaseService.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/loyalty-program")
public class LoyaltyProgramController {

    @Autowired
    private LoyaltyProgramService loyaltyProgramService;

    @PostMapping
    public ResponseEntity<LoyaltyProgram> save(@RequestBody LoyaltyProgram loyaltyProgramParameter) {
        LoyaltyProgram loyaltyProgram = loyaltyProgramService.save(loyaltyProgramParameter);

        if (loyaltyProgram == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(loyaltyProgram);
    }

}
package rs.ac.uns.acs.nais.GraphDatabaseService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Fan;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.FanService;

import java.util.List;

@RestController
@RequestMapping("/fans")
public class FanController {

    @Autowired
    private FanService fanService;

    @GetMapping
    public ResponseEntity<List<Fan>> findAll(){
        return new ResponseEntity<>(fanService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Fan> createFan(@RequestBody Fan fan){
        return new ResponseEntity<>(fanService.createPlayer(fan), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteFan(@RequestParam Long id){
        if(fanService.deletePlayer(id)){
            return new ResponseEntity<>(true, HttpStatus.FOUND);
        }
        else{
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }
}
